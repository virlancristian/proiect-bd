package com.example.backend.services.dbservice;

import com.example.backend.models.database.Mechanics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetMechanicsService {
    GetMechanicsRepo repo;

    @Autowired
    public GetMechanicsService(GetMechanicsRepo repo) {
        this.repo = repo;
    }

    public List<Mechanics> getMechanicsFromService(String serviceName) {
        return repo.getMechanicsFromService(serviceName);
    }

    public List<Mechanics> getMechanicsWorkingOnCar(String serviceName, String brandName, String modelName) {
        return repo.getMechanicsWorkingOnCar(serviceName, brandName, modelName);
    }

    public List<Mechanics> getAllMechanics() {
        return repo.getAllMechanics();
    }
}
