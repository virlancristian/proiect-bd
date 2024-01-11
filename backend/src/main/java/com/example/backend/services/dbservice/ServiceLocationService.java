package com.example.backend.services.dbservice;

import com.example.backend.models.database.ServiceLocations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceLocationService {
    ServiceLocationRepo repo;

    @Autowired
    public ServiceLocationService(ServiceLocationRepo repo) {
        this.repo = repo;
    }

    public List<ServiceLocations> getAllServices() {
        return repo.getAllServices();
    }
}
