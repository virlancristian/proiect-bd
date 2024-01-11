package com.example.backend.services.dbservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class MechanicModifyService {
    Connection dbConnection;
    Statement statement;

    public MechanicModifyService(@Value("${db.username}") String dbUsername,
                            @Value("${db.password}") String dbPassword) {
        try {
            dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/carservicedb", dbUsername, dbPassword);
            statement = dbConnection.createStatement();

            System.out.println("Successfully connected to database!");
        } catch(SQLException error) {
            System.out.println("Unable to connect to database" + error);
        }
    }

    public void insertMechanic(String firstName,
                               String lastName,
                               Integer workingLocation) {
        String query = "INSERT INTO mechanics(First_Name, Last_Name, Working_Location) " +
                        "VALUES('" + firstName + "', '" + lastName + "', " + workingLocation.toString() + ");";
        System.out.println(query);
        try {
            statement.execute(query);
        } catch(SQLException error) {
            System.out.println("Error while inserting mechanic into database: " + error);
        }
    }

    public void updateMechanic(String firstName,
                               String lastName,
                               Integer workingLocation) {
        String query = "UPDATE mechanics " +
                        "SET Working_Location = " + workingLocation.toString() +
                        " WHERE First_Name = '" + firstName + "' AND Last_name = '" + lastName + "';";
        System.out.println(query);
        try {
            statement.execute(query);
        } catch(SQLException error) {
            System.out.println("Error while updating mechanic: " + error);
        }
    }

    public void deleteMechanic(String firstName,
                               String lastName,
                               Integer workingLocation) {
        String query = "DELETE FROM mechanics " +
                        "WHERE First_Name = '" + firstName + "' AND Last_Name = '" + lastName + "' AND Working_Location = " + workingLocation.toString() + ";";
        String query2 = "DELETE FROM assigned_mechanics_to_cars A\n" +
                "WHERE A.MechanicID = (SELECT MechanicID FROM mechanics WHERE First_Name = '" + firstName + "' AND Last_Name = '" + lastName + "')";
        System.out.println(query);
        try {
            statement.execute(query2);
            statement.execute(query);
        } catch(SQLException error) {
            System.out.println("Error while deleting mechanic: " + error);
        }
    }
}
