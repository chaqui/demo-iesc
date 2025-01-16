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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.pagos.dto.DtoClient;
import com.demo.pagos.dto.DtoInspection;
import com.demo.pagos.models.Client;
import com.demo.pagos.services.ClientService;
import com.demo.pagos.services.InsepctionService;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/clients")
@Log
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private InsepctionService inspectionService;

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

    @GetMapping("/{clientId}/inspections")
    public ResponseEntity<List<DtoInspection.Get>> getInspectionsByClientId(@RequestParam Long clientId) {
        log.info("Getting inspections by client id");
        return new ResponseEntity<List<DtoInspection.Get>>(
                this.inspectionService.getInspectionsByClientId(clientId).stream().map(DtoInspection.Get::new)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }
}
