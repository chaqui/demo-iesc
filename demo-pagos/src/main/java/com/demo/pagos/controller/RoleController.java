package com.demo.pagos.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.pagos.dto.DtoRole;
import com.demo.pagos.exception.HttpException;
import com.demo.pagos.services.RoleService;


@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * Get all roles
     * @return Role's list
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DtoRole.Get> getRoles() {
        return roleService.getRole().stream().map(DtoRole.Get::new).toList();
    }

    /**
     * Save role
     * @param role Role data
     * @throws HttpException if role already exists
     */
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void saveRole(@RequestBody DtoRole.Post role) throws HttpException {
        roleService.saveRole(role);
    }

}
