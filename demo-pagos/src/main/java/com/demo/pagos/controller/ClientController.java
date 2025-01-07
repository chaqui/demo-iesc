package com.demo.pagos.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.pagos.dto.DtoClient;
import com.demo.pagos.models.Client;
import com.demo.pagos.services.ClientService;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/clients")
@Log
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<DtoClient.Get> saveClient(@RequestBody DtoClient.Post client) {
        Client clientResponse = this.clientService.saveClient(client);
        return new ResponseEntity<DtoClient.Get>(new DtoClient.Get(clientResponse), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DtoClient.Get>> getClients() {
        log.info("Getting clients");
        return new ResponseEntity<List<DtoClient.Get>>(
                this.clientService.getClients().stream().map(DtoClient.Get::new).collect(Collectors.toList()),
                HttpStatus.OK);
    }
}
