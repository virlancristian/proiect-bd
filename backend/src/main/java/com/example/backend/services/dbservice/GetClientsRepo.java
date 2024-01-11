package com.example.backend.services.dbservice;

import com.example.backend.models.database.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GetClientsRepo extends JpaRepository<Client, Integer> {
    //INTEROGARE SIMPLA 5 - Selecteaza clientii care au o programare intr-o anumita zi
    @Query(value = "SELECT A.*\n" +
            "FROM clients A\n" +
            "JOIN cars_in_service B ON B.Car_Owner = A.ClientID\n" +
            "JOIN schedules C ON B.CarID = C.Assigned_Car\n" +
            "WHERE C.Scheduled_Day = :date", nativeQuery = true)
    List<Client> getClientsScheduled(@Param("date")String date);

    @Query(value = "SELECT * FROM clients", nativeQuery = true)
    List<Client> getAllClients();

    @Query(value = "SELECT ClientID from clients WHERE Email=:email", nativeQuery = true)
    Integer getClientID(@Param("email") String email);
}
