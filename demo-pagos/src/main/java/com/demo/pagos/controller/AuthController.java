package com.demo.pagos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.demo.pagos.dto.DtoToken;
import com.demo.pagos.dto.DtoUser;
import com.demo.pagos.exception.HttpException;
import com.demo.pagos.services.UserService;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/auth")
@Log
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<DtoToken> login(@RequestBody DtoUser.Login dtoUser) throws HttpException {
        String token = userService.login(dtoUser);
        
        if (token != null) {
            log.info(token);
            DtoToken dtoToken = new DtoToken(token);
            log.info(dtoToken.toString());
            return new ResponseEntity<DtoToken>(dtoToken, HttpStatus.OK);
        }
        throw new HttpException("Datos Invalidos", HttpStatus.UNAUTHORIZED);

    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public void register(@RequestBody DtoUser.Post dtoUser) throws HttpException {
        userService.saveUser(dtoUser);
    }

}
