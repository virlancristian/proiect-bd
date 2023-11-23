package com.example.backendandapi.api.controllers;

import com.example.backendandapi.models.api.ServiceLocations;
import com.example.backendandapi.services.response.filter.ServiceFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetServiceLocations {
    ServiceFilter serviceFilter;

    @Autowired
    public GetServiceLocations(ServiceFilter serviceFilter) {
        this.serviceFilter = serviceFilter;
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/api/service_locations")
    public List<ServiceLocations> getServiceLocations() {
        return serviceFilter.filterResponse();
    }
}
