package com.demo.pagos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.pagos.exception.HttpException;
import com.demo.pagos.models.TypeInspection;
import com.demo.pagos.repository.TypeInspectionRepository;

@Service
public class TypeInspectionService {

    @Autowired
    private TypeInspectionRepository typeInspectionRepository;

    public List<TypeInspection> getAll() {
        return typeInspectionRepository.findAll();
    }

    public TypeInspection getById(Long id) throws HttpException {
        return typeInspectionRepository.findById(id).orElseThrow(() -> new HttpException("Type inspection not found", null));
    }
    
}
