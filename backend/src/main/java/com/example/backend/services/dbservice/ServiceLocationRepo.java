package com.example.backend.services.dbservice;

import com.example.backend.models.database.ServiceLocations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceLocationRepo extends JpaRepository<ServiceLocations, Integer> {
    @Query(value = "SELECT * FROM service_locations", nativeQuery = true)
    List<ServiceLocations> getAllServices();
}
