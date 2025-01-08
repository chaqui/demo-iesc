package com.demo.pagos.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import lombok.extern.java.Log;

@Controller
@Log
public class SocketController {
  private final SimpMessagingTemplate messagingTemplate;

    public SocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/payments")
    @SendTo("/topic/payments")
    public String updatePayment(String message) {
        log.info(message);
        return message;
    }

    public void sendUpdate(String message) {
        messagingTemplate.convertAndSend("/topic/payments", message);
    }

}
