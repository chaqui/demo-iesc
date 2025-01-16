package com.demo.pagos.dto;

import com.demo.pagos.models.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class DtoRole {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post{

        protected Long id;
        protected String name;
    }

    @Getter
    @Setter
    public static class Get extends Post{

        public Get(Role role){
            this.id = role.getId();
            this.name = role.getName();
        }
    }
}
