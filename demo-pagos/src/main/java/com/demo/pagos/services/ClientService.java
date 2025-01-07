package com.demo.pagos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.pagos.dto.DtoClient;
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

}
