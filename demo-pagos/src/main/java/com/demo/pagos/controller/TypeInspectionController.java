package com.demo.pagos.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.pagos.dto.DtoTypeInspections;
import com.demo.pagos.services.TypeInspectionService;

import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/type-inspections")
public class TypeInspectionController {

    @Autowired
    private TypeInspectionService typeInspectionService;


    @GetMapping
    public ResponseEntity<List<DtoTypeInspections.Get>> getTypeInspections() {
        log.info("Getting type inspections");
        List<DtoTypeInspections.Get> inspections = this.typeInspectionService.getAll().stream()
            .map(typeInspection -> new DtoTypeInspections.Get(typeInspection))
            .collect(Collectors.toList());
        return ResponseEntity.ok(inspections);
    }
}
