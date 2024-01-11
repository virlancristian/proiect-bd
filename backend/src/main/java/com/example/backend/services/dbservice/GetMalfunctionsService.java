package com.example.backend.services.dbservice;

import com.example.backend.models.database.Malfunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetMalfunctionsService {
    GetMalfunctionsRepo repo;

    @Autowired
    public GetMalfunctionsService(GetMalfunctionsRepo repo) {
        this.repo = repo;
    }

    public List<Malfunction> getCarMalfunctions(String brand,
                                                String model,
                                                Integer serviceID,
                                                Integer ownerID) {
        return repo.getCarMalfunctions(brand, model, serviceID, ownerID);
    }

    public List<Malfunction> getAllMalfunctions() {
        return repo.getAllMalfunctions();
    }
}
