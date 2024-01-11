package com.example.backend.services.dbservice;

import com.example.backend.models.database.Malfunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GetMalfunctionsRepo extends JpaRepository<Malfunction, Integer> {
    //INTEROGARE SIMPLA 1 - Selecteaza toate defectiunile unei masini
    @Query(value = "SELECT A.*\n" +
            "FROM cars_in_service C\n" +
            "JOIN car_malfunctions B ON B.CarID = C.CarID\n" +
            "JOIN possible_malfunctions A ON A.MalfunctionID = B.MalfunctionID\n" +
            "WHERE C.Brand_Name = :brand AND C.Model_Name = :model AND C.Assigned_Service_Location = :serviceID AND C.Car_Owner = :ownerID\n" +
            "ORDER BY A.Malfunction", nativeQuery = true)
    List<Malfunction> getCarMalfunctions(@Param("brand") String brand,
                                         @Param("model") String model,
                                         @Param("serviceID") Integer serviceID,
                                         @Param("ownerID") Integer ownerID);

    @Query(value = "SELECT * FROM possible_malfunctions", nativeQuery = true)
    List<Malfunction> getAllMalfunctions();
}
