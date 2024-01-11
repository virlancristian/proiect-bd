package com.example.backend.services.dbservice;

import com.example.backend.models.database.Mechanics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GetMechanicsRepo extends JpaRepository<Mechanics, Integer> {
    //INTEROGARE SIMPLA 4 - selectaza toti mecanicii dintr-un service
    @Query(value = "SELECT A.*\n" +
            "FROM mechanics A\n" +
            "JOIN service_locations B ON B.ServiceLocationID = A.Working_Location\n" +
            "WHERE B.Name = :serviceName", nativeQuery = true)
    List<Mechanics> getMechanicsFromService(@Param("serviceName") String serviceName);

    //INTEROGARE COMPLEXA 2 - selecteaza toti mecanicii dintr-un service care lucreaza la o anumita masina
    @Query(value = "SELECT C.*\n" +
            "FROM mechanics C\n" +
            "JOIN assigned_mechanics_to_cars D ON C.MechanicID = D.MechanicID\n" +
            "JOIN (\n" +
            "\tSELECT A.*\n" +
            "\tFROM cars_in_service A \n" +
            "\tJOIN service_locations B ON B.ServiceLocationID = A.Assigned_Service_Location\n" +
            "\tWHERE B.Name = :serviceName AND A.Brand_Name = :brandName AND A.Model_Name = :modelName\n" +
            ") E ON E.CarID = D.CarID\n" +
            "ORDER BY C.First_Name, C.Last_Name", nativeQuery = true)
    List<Mechanics> getMechanicsWorkingOnCar(@Param("serviceName") String serviceName,
                                             @Param("brandName") String brandName,
                                             @Param("modelName") String modelName);

    @Query(value = "SELECT * FROM mechanics", nativeQuery = true)
    List<Mechanics> getAllMechanics();
}
