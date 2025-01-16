package com.demo.pagos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.demo.pagos.dto.DtoClient;
import com.demo.pagos.exception.HttpException;
import com.demo.pagos.models.Client;
import com.demo.pagos.repository.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client saveClient(DtoClient.Post client) {
        return clientRepository.save(new Client(client));
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client getById(Long id) throws HttpException {
        return clientRepository.findById(id)
                .orElseThrow(() -> new HttpException("Client not found", HttpStatus.NOT_FOUND));
    }

}
