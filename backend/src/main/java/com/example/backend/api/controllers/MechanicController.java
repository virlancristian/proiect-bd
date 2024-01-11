package com.example.backend.api.controllers;

import com.example.backend.models.database.Mechanics;
import com.example.backend.services.dbservice.GetMechanicsService;
import com.example.backend.services.dbservice.MechanicModifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MechanicController {
    GetMechanicsService service;
    MechanicModifyService service1;

    @Autowired
    public MechanicController(GetMechanicsService service, MechanicModifyService service1) {
        this.service = service;
        this.service1 = service1;
    }

    @CrossOrigin
    @GetMapping("/api/mechanics/get")
    public ResponseEntity<List<Mechanics>> getMechanics(@RequestParam(value = "serviceName", required = false) String serviceName,
                                                        @RequestParam(value = "brand", required = false) String brand,
                                                        @RequestParam(value = "model", required = false) String model) {
        if(serviceName != null && brand == null && model == null) {
            return ResponseEntity.status(200).body(service.getMechanicsFromService(serviceName));
        } else if(brand != null && model != null){
            return ResponseEntity.status(200).body(service.getMechanicsWorkingOnCar(serviceName, brand, model));
        }

        return ResponseEntity.status(200).body(service.getAllMechanics());
    }

    @CrossOrigin
    @PostMapping("/api/mechanics/add")
    public ResponseEntity<String> addMechanic(@RequestParam("firstName") String firstName,
                                              @RequestParam("lastName") String lastName,
                                              @RequestParam("workingLocation") Integer workingLocation) {
        service1.insertMechanic(firstName, lastName, workingLocation);
        return ResponseEntity.status(200).body(null);
    }

    @CrossOrigin
    @PostMapping("/api/mechanics/update")
    public ResponseEntity<String> updateMechanic(@RequestParam("firstName") String firstName,
                                                 @RequestParam("lastName") String lastName,
                                                 @RequestParam("workingLocation") Integer workingLocation) {
        service1.updateMechanic(firstName, lastName, workingLocation);
        return ResponseEntity.status(200).body(null);
    }

    @CrossOrigin
    @PostMapping("/api/mechanics/delete")
    public ResponseEntity<String> deleteMechanic(@RequestParam("firstName") String firstName,
                                                 @RequestParam("lastName") String lastName,
                                                 @RequestParam("workingLocation") Integer workingLocation) {
        service1.deleteMechanic(firstName, lastName, workingLocation);
        return ResponseEntity.status(200).body(null);
    }
}
