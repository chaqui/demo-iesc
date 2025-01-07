package com.demo.pagos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.demo.pagos.dto.DtoRole;
import com.demo.pagos.exception.HttpException;
import com.demo.pagos.models.Role;
import com.demo.pagos.repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Get all roles
     * @return Role's list
     */
    public List<Role> getRole() {
        return roleRepository.findAll();
    }

    /**
     * Get role by id
     * @param id Role id
     * @return Role
     * @throws HttpException if role not found
     */
    public Role getRoleById(Long id) throws HttpException {
        return roleRepository.findById(id).orElseThrow(
            () -> new HttpException("Rol no existe", HttpStatus.NOT_FOUND)
        );
    }

    /**
     * Save role
     * @param role Role data
     * @throws HttpException if role already exists
     */
    public void saveRole(DtoRole.Post role) throws HttpException {
        Role roleExist = roleRepository.findById(role.getId()).orElse(
            null
        );
        if (roleExist != null) {
            throw new HttpException("Rol ya existe", HttpStatus.BAD_REQUEST);
        }
        roleRepository.save(new Role(role));
    }

}
