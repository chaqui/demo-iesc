package com.demo.pagos.dto;

import com.demo.pagos.models.Client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class DtoClient {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post{
        protected String name;
        protected String email;
        protected String phone;
        protected String address;

    }

    @Getter
    @Setter
    public static class Get extends Post{
        private Long id;

        public Get(Client client){
            this.id = client.getId();
            this.name = client.getName();
            this.email = client.getEmail();
            this.phone = client.getPhone();
            this.address = client.getAddress();
        }
    }
}
