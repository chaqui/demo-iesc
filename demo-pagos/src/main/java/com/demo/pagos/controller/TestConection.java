package com.demo.pagos.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/test")
public class TestConection {

    @GetMapping
    public String test() {
        return "Test Conection";
    }

}
