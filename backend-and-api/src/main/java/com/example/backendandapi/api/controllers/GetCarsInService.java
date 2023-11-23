package com.example.backendandapi.api.controllers;

import com.example.backendandapi.models.api.CarsInService;
import com.example.backendandapi.services.response.filter.CarsInServiceFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetCarsInService {
    CarsInServiceFilter carsInServiceFilter;

    @Autowired
    public GetCarsInService(CarsInServiceFilter filter) {
        this.carsInServiceFilter = filter;
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/api/cars_in_service")
    public List<CarsInService> getCarsInService(@RequestParam int id) {
        return carsInServiceFilter.filterResponse(id);
    }
}
