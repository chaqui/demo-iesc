package com.demo.pagos.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.pagos.dto.DtoInspection;
import com.demo.pagos.exception.HttpException;
import com.demo.pagos.services.InsepctionService;

@Controller
@RequestMapping("/inspections")
public class InspectionController {

    @Autowired
    private InsepctionService inspectionService;

    @GetMapping
    public ResponseEntity<List<DtoInspection.Get>> getInspections() {
        return new ResponseEntity<List<DtoInspection.Get>>(this.inspectionService.getInspections().stream()
                .map(DtoInspection.Get::new).collect(Collectors.toList()), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<DtoInspection.Get> createInspection(@RequestBody DtoInspection.Post inspection)
            throws HttpException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return new ResponseEntity<DtoInspection.Get>(
                new DtoInspection.Get(this.inspectionService.createInspection(inspection, username)),
                HttpStatus.CREATED);
    }

}
