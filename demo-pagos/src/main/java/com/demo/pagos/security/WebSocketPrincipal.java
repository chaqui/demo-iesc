package com.demo.pagos.security;

import java.security.Principal;

public class WebSocketPrincipal implements Principal{

    private String name;

    public WebSocketPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}
