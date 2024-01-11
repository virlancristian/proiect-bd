package com.example.backend.services.dbservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class ClientService {
    Connection dbConnection;
    Statement statement;

    public ClientService(@Value("${db.username}") String dbUsername,
                            @Value("${db.password}") String dbPassword) {
        try {
            dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/carservicedb", dbUsername, dbPassword);
            statement = dbConnection.createStatement();

            System.out.println("Successfully connected to database!");
        } catch(SQLException error) {
            System.out.println("Unable to connect to database" + error);
        }
    }

    public void insertClient(String First_name,
                             String Last_name,
                             String email,
                             String phone,
                             String address,
                             Integer number1,
                             String number2,
                             Integer apartment,
                             String city) {
        String query = "INSERT INTO clients(First_Name, Last_Name, Email, Phone_Number, Street_Address, Street_Number, Flat_Number, Apartment, City) " +
                        "VALUES('" + First_name + "', '" + Last_name + "', '" + email + "', '" + phone   + "', '" + address + "', " + number1.toString() + ", '" + number2 + "', " + apartment.toString() + ", '" + city + "')";
        try {
            statement.execute(query);
        } catch(SQLException error) {
            System.out.println("Error in updating car: " + error);
        }
    }
}
