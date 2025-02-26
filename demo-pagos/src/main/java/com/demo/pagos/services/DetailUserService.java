package com.demo.pagos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.pagos.models.User;
import com.demo.pagos.repository.UserRepository;
import com.demo.pagos.security.CustomUserDetails;

@Service
public class DetailUserService implements UserDetailsService {
 @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return new CustomUserDetails(user);
    }
}
