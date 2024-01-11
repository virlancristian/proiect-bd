package com.example.backendandapi.services.dbservice;

import com.example.backendandapi.models.database.CarsInService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<CarsInService, Long> {
    @Query(value = "INSERT INTO cars_in_service(Car_Owner, Brand_Name, Model_Name, Year_of_fabrication, Engine_Size, Fuel, Horse_Power)" +
                    "VALUES( :carOwner, :brand, :model, :yearOfFabrication, :engineSize, :fuel, :horsePower)", nativeQuery = true)
    void addCarNativeQuery(@Param("carOwner") int carOwner,
                           @Param("brand") String brand,
                           @Param("model") String model,
                           @Param("yearOfFabrication") int yearOfFabrication,
                           @Param("engineSize") float engineSize,
                           @Param("fuel") String fuel,
                           @Param("horsePower") int horsePower);
}
