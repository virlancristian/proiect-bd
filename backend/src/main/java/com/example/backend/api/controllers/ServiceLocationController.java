package com.example.backend.api.controllers;

import com.example.backend.models.database.ServiceLocations;
import com.example.backend.services.dbservice.ServiceLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceLocationController {
    ServiceLocationService service;

    @Autowired
    public ServiceLocationController(ServiceLocationService service) {
        this.service = service;
    }

    @CrossOrigin
    @GetMapping("/api/services/get")
    public ResponseEntity<List<ServiceLocations>> getAllServices() {
        return ResponseEntity.status(200).body(service.getAllServices());
    }
}
