package com.demo.pagos.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.pagos.dto.DtoDetailPay;
import com.demo.pagos.dto.DtoPay;
import com.demo.pagos.exception.HttpException;
import com.demo.pagos.models.DetailPay;
import com.demo.pagos.services.DetailPayService;
import com.demo.pagos.services.PayService;

@Controller
@RequestMapping("/pays")
public class PayController {

    @Autowired
    private PayService payService;

    @Autowired
    private DetailPayService detailPayService;

    @PostMapping
    public ResponseEntity<DtoPay.Get> createPay(@RequestBody DtoPay.Post pay) throws HttpException {
        return new ResponseEntity<DtoPay.Get>(new DtoPay.Get(this.payService.savePay(pay)), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DtoPay.Get>> getPays() {
        return new ResponseEntity<List<DtoPay.Get>>(this.payService.getPays().stream().map(DtoPay.Get::new)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PutMapping("{id}/pay")
    public ResponseEntity<DtoPay.Get> pay(@PathVariable Long id) throws HttpException {
        return new ResponseEntity<DtoPay.Get>(new DtoPay.Get(this.payService.payPay(id)), HttpStatus.OK);
    }

    @PutMapping("{id}/send")
    public ResponseEntity<DtoPay.Get> send(@PathVariable Long id) throws HttpException {
        return new ResponseEntity<DtoPay.Get>(new DtoPay.Get(this.payService.sentPay(id)), HttpStatus.OK);
    }

    @GetMapping("/activate-transmission")
    public void activateTransmission() {
        this.payService.sendPays();
    }

    @GetMapping("/{id}/detail-pays")
    public ResponseEntity<List<DtoDetailPay.Get>> getDetailPays(@PathVariable Long id) {
        return new ResponseEntity<List<DtoDetailPay.Get>>(this.detailPayService.getDetailsByPayId(id).stream().map(DtoDetailPay.Get::new)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<List<DtoPay.Get>> getByStatus(@RequestParam Long status) {
        return new ResponseEntity<List<DtoPay.Get>>(this.payService.getByStatus(status).stream().map(DtoPay.Get::new)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

}
