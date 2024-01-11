package com.example.backend.api.controllers;

import com.example.backend.models.database.CarsAndPieces;
import com.example.backend.models.database.CarsAndRepairCost;
import com.example.backend.models.database.CarsInService;
import com.example.backend.services.dbservice.CarModifyService;
import com.example.backend.services.dbservice.GetCarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {
    GetCarsService service;
    CarModifyService service1;

    @Autowired
    public CarController(GetCarsService service, CarModifyService service1) {
        this.service = service;
        this.service1 = service1;
    }

    @CrossOrigin
    @GetMapping("/api/cars/get")
    public ResponseEntity<List<CarsInService>> getCars(@RequestParam(value = "serviceName", required = false) String serviceName,
                                                       @RequestParam(value = "firstName", required = false) String firstName,
                                                       @RequestParam(value = "lastName", required = false) String lastName,
                                                       @RequestParam(value = "date", required = false) String date,
                                                       @RequestParam(value = "scheduled", required = false) boolean isScheduled) {
        if(serviceName != null && date == null) {
            return ResponseEntity.status(200).body(service.getCarsFromService(serviceName));
        }

        if(firstName != null && lastName != null) {
            return ResponseEntity.status(200).body(service.getCarsOfClient(firstName, lastName));
        }

        if(date != null) {
            return ResponseEntity.status(200).body(service.getCarsByDate(date, serviceName));
        }

        if(isScheduled) {
            return ResponseEntity.status(200).body(service.getUnscheduledCars());
        }



        return ResponseEntity.status(200).body(service.getAllCars());
    }

    @CrossOrigin
    @PostMapping("/api/cars/add")
    public ResponseEntity<String> insertCar(@RequestParam("carOwner") int carOwner,
                                            @RequestParam("brand") String brand,
                                            @RequestParam("model") String model,
                                            @RequestParam("year") int yearOfFabrication,
                                            @RequestParam("engine") float engineSize,
                                            @RequestParam("fuel") String fuel,
                                            @RequestParam("hp") int horsePower) {
        service1.insertCar(carOwner, brand, model, yearOfFabrication, engineSize, fuel, horsePower);
        return ResponseEntity.status(200).body(null);
    }

    @CrossOrigin
    @PostMapping("/api/cars/update")
    public ResponseEntity<String> updateCar(@RequestParam("carOwner") Integer carOwner,
                                            @RequestParam("brand") String brand,
                                            @RequestParam("model") String model,
                                            @RequestParam("assignedService") Integer assignedService) {
        service1.updateCar(carOwner, brand, model, assignedService);
        return ResponseEntity.status(200).body(null);
    }

    @CrossOrigin
    @PostMapping("/api/cars/delete")
    public ResponseEntity<String> deleteCar(@RequestParam("carOwner") Integer carOwner,
                                            @RequestParam("brand") String brand,
                                            @RequestParam("model") String model,
                                            @RequestParam("assignedService") Integer assignedService) {
        service1.deleteCar(carOwner, assignedService,brand, model);
        return ResponseEntity.status(200).body(null);
    }

    @CrossOrigin
    @GetMapping("/api/cars/cost/get")
    public ResponseEntity<List<CarsAndRepairCost>> getRepairCost(@RequestParam("firstName") String firstName,
                                                                 @RequestParam("lastName") String lastName) {
        return ResponseEntity.status(200).body(service.getRepairCost(firstName, lastName));
    }

    @CrossOrigin
    @GetMapping("/api/cars/nostock/get")
    public ResponseEntity<List<CarsAndPieces>> getNoStock() {
        return ResponseEntity.status(200).body(service.getCarsWithNoStock());
    }
}
