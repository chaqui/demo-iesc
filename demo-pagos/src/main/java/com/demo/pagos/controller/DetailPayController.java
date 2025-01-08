package com.demo.pagos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.pagos.dto.DtoDetailPay;
import com.demo.pagos.exception.HttpException;
import com.demo.pagos.services.DetailPayService;

@Controller
@RequestMapping("/detail-pays")
public class DetailPayController {

    @Autowired
    private DetailPayService detailPayService;

    @PostMapping
    public ResponseEntity<DtoDetailPay.Get> createDetailPay(@RequestBody DtoDetailPay.Post detailPay) throws HttpException {
        return new ResponseEntity<DtoDetailPay.Get>(new DtoDetailPay.Get(this.detailPayService.createDetail(detailPay)), HttpStatus.CREATED);
    }
}
