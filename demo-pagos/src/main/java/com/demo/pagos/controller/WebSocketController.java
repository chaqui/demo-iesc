package com.demo.pagos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.demo.pagos.dto.DtoPay;
import com.demo.pagos.services.PayService;

import lombok.extern.java.Log;

@Controller
@Log
public class WebSocketController {

    @Autowired
    private PayService payService;

    @MessageMapping("/sendPay")
    @SendTo("/topic/payments")
    public DtoPay.Get sendPayment(DtoPay.Get paymentMessage) {
        return paymentMessage;
    }

    @MessageMapping("/payments/succesfull")
    public void handleClientResponse(Long id) {
        try{
            log.info("Payment with id: " + id + " was succesfull");
            this.payService.sentPay(id);
        }
        catch(Exception e){
            log.info("Error sending payment with id: " + id);
        }
    }
}
