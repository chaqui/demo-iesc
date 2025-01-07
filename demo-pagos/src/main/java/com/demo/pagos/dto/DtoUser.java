package com.demo.pagos.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class DtoUser {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Login{
        private String name;
        private String password;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post extends Login{
        private Long roleId;
                
    }


}
