package com.demo.pagos.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import lombok.extern.java.Log;

@Controller
@Log
public class SocketController {

    @MessageMapping("/payments")
    @SendTo("/topic/messages")
    public String handle(String message) {
        log.info(message);
        return message;
    }

}
