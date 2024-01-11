package com.example.backend.services.dbservice;

import com.example.backend.models.database.CarsAndRepairCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepairCostRepo extends JpaRepository<CarsAndRepairCost, Integer> {
    //INTEROGARE COMPLEXA 3 - selecteaza toata masinile unui client, si costul total de reparatii al fiecarei masini
    @Query(value = "SELECT F.*, SUM(C.Price) AS RepairCost\n" +
            "FROM ( \n" +
            "\tSELECT B.*\n" +
            "\tFROM cars_in_service B\n" +
            "\tJOIN clients A ON A.ClientID = B.Car_Owner\n" +
            "\tWHERE A.First_Name = :firstName AND A.Last_Name = :lastName\n" +
            ") F\n" +
            "JOIN car_malfunctions E ON F.CarID = E.CarID\n" +
            "JOIN possible_malfunctions D ON D.MalfunctionID = E.MalfunctionID\n" +
            "JOIN car_parts C ON D.Damaged_component = C.Piece_Name\n" +
            "GROUP BY F.CarID\n" +
            "ORDER BY F.Brand_Name, F.Model_Name ASC", nativeQuery = true)
    List<CarsAndRepairCost> getRepairCost(@Param("firstName") String firstName,
                                          @Param("lastName") String lastName);
}
