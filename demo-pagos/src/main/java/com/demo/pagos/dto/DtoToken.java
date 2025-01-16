package com.demo.pagos.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoToken {

    private String token;

    public DtoToken(String token) {
        this.token = token;
    }
}
