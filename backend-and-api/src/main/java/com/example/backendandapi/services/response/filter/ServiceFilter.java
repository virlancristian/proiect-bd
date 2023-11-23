package com.example.backendandapi.services.response.filter;

import com.example.backendandapi.models.api.ServiceLocations;
import com.example.backendandapi.services.dbservice.Dbservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceFilter {
    Dbservice dbservice;

    @Autowired
    public  ServiceFilter(Dbservice dbservice) {
        this.dbservice = dbservice;
    }

    public List<ServiceLocations> filterResponse() {
        ResultSet result = dbservice.getItems("selectServiceLocations", "");
        List<ServiceLocations> serviceLocations = new ArrayList<>();

        try {
            while(result.next()) {
                serviceLocations.add(new ServiceLocations(result.getInt("ServiceLocationID"), result.getString("Name")));
            }
        } catch (SQLException error) {
            System.out.println("Unable to retrieve data from database: " + error);
        }

        return serviceLocations;
    }
}
