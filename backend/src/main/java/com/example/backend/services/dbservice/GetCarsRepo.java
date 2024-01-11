package com.example.backend.services.dbservice;

import com.example.backend.models.database.CarsAndRepairCost;
import com.example.backend.models.database.CarsInService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface GetCarsRepo extends JpaRepository<CarsInService, Integer> {
    //INTEROGARE SIMPLA 2 - Selecteaza toate masinile dintr-un anumit service
    @Query(value = "SELECT A.* \n" +
            "FROM cars_in_service A\n" +
            "RIGHT JOIN service_locations B on A.Assigned_Service_Location = B.ServiceLocationID\n" +
            "WHERE B.Name = :serviceName", nativeQuery = true)
    List<CarsInService> getCarsFromService(@Param("serviceName") String serviceName);

    //INTEROGARE SIMPLA 3 - Selecteaza toate masinile care nu sunt programate
    @Query(value = "SELECT c.*\n" +
            "FROM cars_In_service c\n" +
            "LEFT JOIN schedules s ON c.CarID = s.Assigned_Car\n" +
            "WHERE s.ScheduleID IS NULL OR s.ScheduleID = 0;\n", nativeQuery = true)
    List<CarsInService> getUnscheduledCars();

    //INTEROGARE SIMPLA 6 - Selecteaza toate masinile unui client
    @Query(value = "SELECT A.*\n" +
            "FROM cars_in_service A\n" +
            "JOIN clients B ON B.ClientID = A.Car_Owner\n" +
            "WHERE B.First_Name = :firstName AND B.Last_Name = :lastName\n" +
            "ORDER BY A.Brand_Name, A.Model_Name", nativeQuery = true)
    List<CarsInService> getCarsOfClient(@Param("firstName") String firstName,
                                        @Param("lastName") String lastName);

    //INTEROGARE COMPLEXA 4 - selecteaza toate masinile dintr-un service programate intr-o zi
    @Query(value = "SELECT C.*\n" +
            "FROM (\n" +
            "\tSELECT A.*\n" +
            "\tFROM cars_in_service A\n" +
            "\tJOIN schedules B ON A.CarID = B.Assigned_Car\n" +
            "\tWHERE B.Scheduled_Day = :date\n" +
            ") C\n" +
            "JOIN service_locations D ON C.Assigned_Service_Location = D.ServiceLocationID\n" +
            "WHERE D.Name = :serviceName\n" +
            "ORDER BY C.Brand_Name, C.Model_Name", nativeQuery = true)
    List<CarsInService> getCarsByDate(@Param("date") String date,
                                      @Param("serviceName") String serviceName);

    @Query(value = "SELECT * FROM cars_in_service", nativeQuery = true)
    List<CarsInService> getAllCars();
}
