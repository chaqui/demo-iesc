package com.demo.pagos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.pagos.dto.DtoPay;
import com.demo.pagos.exception.HttpException;
import com.demo.pagos.services.PayService;

@Controller
@RequestMapping("/pays")
public class PayController {

    @Autowired
    private PayService payService;

    @PostMapping
    public ResponseEntity<DtoPay.Get> createPay(@RequestBody DtoPay.Post pay) throws HttpException {
        return new ResponseEntity<DtoPay.Get>(new DtoPay.Get(this.payService.savePay(pay)), HttpStatus.CREATED);
    }

    @GetMapping("/activate-transmission")
    public void activateTransmission() {
        this.payService.sendPays();
    }

}
