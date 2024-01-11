package com.example.backend.services.dbservice;

import com.example.backend.models.database.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetClientsService {
    GetClientsRepo repo;

    @Autowired
    public GetClientsService(GetClientsRepo repo) {
        this.repo = repo;
    }

    public List<Client> getClientsScheduled(String date) {
        return repo.getClientsScheduled(date);
    }

    public List<Client> getAllClients() {
        return repo.getAllClients();
    }

    public Integer getClientID(String email) {
        return repo.getClientID(email);
    }
}
