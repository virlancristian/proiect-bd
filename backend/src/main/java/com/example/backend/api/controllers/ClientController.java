package com.example.backend.api.controllers;

import com.example.backend.models.database.Client;
import com.example.backend.services.dbservice.ClientService;
import com.example.backend.services.dbservice.GetClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {
    GetClientsService service;
    ClientService service1;

    @Autowired
    public ClientController(GetClientsService service, ClientService service1) {
        this.service = service;
        this.service1 = service1;
    }

    @CrossOrigin
    @GetMapping("/api/clients/get")
    public ResponseEntity<List<Client>> getClients(@RequestParam(value = "date", required = false) String date) {
        if(date != null) {
            return ResponseEntity.status(200).body(service.getClientsScheduled(date));
        }

        return ResponseEntity.status(200).body(service.getAllClients());
    }

    @CrossOrigin
    @GetMapping("/api/clients/id/get")
    public ResponseEntity<Integer> getClientID(@RequestParam(value = "email") String email) {
        return ResponseEntity.status(200).body(service.getClientID(email));
    }

    @CrossOrigin
    @PostMapping("/api/clients/add")
    public ResponseEntity<String> addClient(@RequestParam("firstName") String firstName,
                                            @RequestParam("lastName") String lastName,
                                            @RequestParam("email") String email,
                                            @RequestParam("phone") String phone,
                                            @RequestParam("address") String address,
                                            @RequestParam("number1") Integer number1,
                                            @RequestParam("number2") String number2,
                                            @RequestParam("apartment") Integer apartment,
                                            @RequestParam("city") String city) {
        service1.insertClient(firstName, lastName, email, phone, address, number1, number2, apartment, city);
        return ResponseEntity.status(200).body(null);
    }
}
