package com.demo.pagos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.pagos.dto.DtoInspection;
import com.demo.pagos.exception.HttpException;
import com.demo.pagos.models.Client;
import com.demo.pagos.models.Inspection;
import com.demo.pagos.models.TypeInspection;
import com.demo.pagos.models.User;
import com.demo.pagos.repository.InspectionRepository;

@Service
public class InsepctionService {

    @Autowired
    private InspectionRepository inspectionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private TypeInspectionService typeInspectionService;

    public Inspection createInspection(DtoInspection.Post inspection, String username) throws HttpException {
        User user = userService.findByUsername(username);
        Client client = clientService.getById(inspection.getClientId());
        TypeInspection typeInspection = typeInspectionService.getById(inspection.getTypeInspectionId());

        Inspection newInspection = new Inspection(typeInspection, user, client);
        return inspectionRepository.save(newInspection);

    }

    public List<Inspection> getInspectionsByClientId(Long clientId) {
        return inspectionRepository.findByClientId(clientId);
    }

    public List<Inspection> getInspections() {
        return inspectionRepository.findAll();
    }

}
