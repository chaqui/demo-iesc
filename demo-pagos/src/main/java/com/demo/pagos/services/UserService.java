package com.demo.pagos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.pagos.dto.DtoUser;
import com.demo.pagos.exception.HttpException;
import com.demo.pagos.models.Role;
import com.demo.pagos.models.User;
import com.demo.pagos.repository.UserRepository;
import com.demo.pagos.security.CustomUserDetails;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;


    /**
     * Save user
     * @param dtoUser User data
     * @throws HttpException if role not found
     */
    public void saveUser(DtoUser.Post dtoUser) throws HttpException {
        Role role = roleService.getRoleById(dtoUser.getRoleId());
        User userExist = userRepository.findByUsername(dtoUser.getName());
        if (userExist != null) {
            throw new HttpException("Usuario ya existe", HttpStatus.BAD_REQUEST);
        }
        User user = new User(dtoUser, role);
        user.setPassword(passwordEncoder.encode(dtoUser.getPassword()));
        userRepository.save(user);
    }

    /**
     * Login
     * @param dtoUser User data
     * @return JWT token
     * @throws HttpException if user not found or password is incorrect
     */
    public String login(DtoUser.Login dtoUser) throws HttpException {
        User user = userRepository.findByUsername(dtoUser.getName());
        if (user != null && passwordEncoder.matches(dtoUser.getPassword(), user.getPassword())) {
           
            String token = jwtService.generateToken(user);
            return token;
        }
        throw new HttpException("Datos Invalidos", HttpStatus.UNAUTHORIZED);
    }

    /**
     * Get user by id
     * @param id User id
     * @return User
     * @throws HttpException if user not found
     */
    public User getUserById(Long id) throws HttpException {
        return userRepository.findById(id).orElseThrow(() -> new HttpException("Usuario no encontrado", HttpStatus.NOT_FOUND));
    }


}
