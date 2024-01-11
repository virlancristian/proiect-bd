package com.example.backend.services.dbservice;

import com.example.backend.models.database.CarsAndPieces;
import com.example.backend.models.database.CarsAndRepairCost;
import com.example.backend.models.database.CarsInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCarsService {
    GetCarsRepo repo;
    RepairCostRepo repo1;
    NoStockRepo repo2;

    @Autowired
    public GetCarsService(GetCarsRepo repo, RepairCostRepo repo1, NoStockRepo repo2) {
        this.repo = repo;
        this.repo1 = repo1;
        this.repo2 = repo2;
    }

    public List<CarsInService> getCarsFromService(String serviceName) {
        return repo.getCarsFromService(serviceName);
    }

    public List<CarsInService> getUnscheduledCars() {
        return repo.getUnscheduledCars();
    }

    public List<CarsInService> getCarsOfClient(String firstName, String lastName) {
        return repo.getCarsOfClient(firstName, lastName);
    }

    public List<CarsInService> getCarsByDate(String date, String serviceName) {
        return repo.getCarsByDate(date, serviceName);
    }

    public List<CarsAndRepairCost> getRepairCost(String firstName, String lastName) {
        return repo1.getRepairCost(firstName, lastName);
    }

    public List<CarsAndPieces> getCarsWithNoStock() {
        return repo2.getCarsWithNoStock();
    }

    public List<CarsInService> getAllCars() {
        return repo.getAllCars();
    }
}
