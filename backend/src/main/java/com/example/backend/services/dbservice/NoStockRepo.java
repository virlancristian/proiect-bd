package com.example.backend.services.dbservice;

import com.example.backend.models.database.CarsAndPieces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoStockRepo extends JpaRepository<CarsAndPieces, Integer> {
    @Query(value = "SELECT D.*, E.Piece_Name\n" +
            "FROM cars_in_service D\n" +
            "JOIN (\n" +
            "\tSELECT A.*, C.Piece_Name\n" +
            "\tFROM car_malfunctions A\n" +
            "\tJOIN possible_malfunctions B ON B.MalfunctionID = A.MalfunctionID\n" +
            "\tJOIN car_parts C ON C.Piece_Name = B.Damaged_component\n" +
            "\tWHERE C.Quantity IS NULL OR C.Quantity = 0\n" +
            ") E ON E.CarID = D.CarID\n" +
            "GROUP BY D.CarID, E.Piece_Name\n" +
            "ORDER BY D.Brand_Name, D.Model_Name ASC", nativeQuery = true)
    List<CarsAndPieces> getCarsWithNoStock();
}
