package com.example.backendandapi.services.response.filter;

import com.example.backendandapi.models.api.CarsInService;
import com.example.backendandapi.services.dbservice.Dbservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarsInServiceFilter {
    Dbservice dbservice;

    @Autowired
    public CarsInServiceFilter(Dbservice dbservice) {
        this.dbservice = dbservice;
    }

    public List<CarsInService> filterResponse(int id) {
        ResultSet result = dbservice.getItems("selectCarsInService", String.valueOf(id));
        List<CarsInService> carsInService = new ArrayList<>();

        try {
            while(result.next()) {
                carsInService.add(new CarsInService(result.getInt("CarID"),
                                                    result.getString("Brand_Name"),
                                                    result.getString("Model_Name"),
                                                    result.getInt("Year_of_fabrication")));
            }
        } catch(SQLException error) {
            System.out.println("Unable to retrieve data from database: " + error);
        }

        return carsInService;
    }
}
