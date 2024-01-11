package com.example.backend.api.controllers;

import com.example.backend.models.database.Malfunction;
import com.example.backend.services.dbservice.GetMalfunctionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MalfunctionController {
    GetMalfunctionsService service;

    @Autowired
    public MalfunctionController(GetMalfunctionsService service) {
        this.service = service;
    }

    @CrossOrigin
    @GetMapping("/api/malfunctions/get")
    public ResponseEntity<List<Malfunction>> getMalfunctions(@RequestParam(value = "brand", required = false) String brand,
                                                             @RequestParam(value = "model", required = false) String model,
                                                             @RequestParam(value = "serviceID", required = false) Integer serviceID,
                                                             @RequestParam(value = "ownerID", required = false) Integer ownerID) {
        if(brand != null && model != null) {
            return ResponseEntity.status(200).body(service.getCarMalfunctions(brand, model, serviceID, ownerID));
        }

        return ResponseEntity.status(200).body(service.getAllMalfunctions());
    }
}
