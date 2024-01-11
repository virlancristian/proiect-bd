package com.example.backend.services.dbservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class CarModifyService {
    Connection dbConnection;
    Statement statement;

    public CarModifyService(@Value("${db.username}") String dbUsername,
                            @Value("${db.password}") String dbPassword) {
        try {
            dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/carservicedb", dbUsername, dbPassword);
            statement = dbConnection.createStatement();

            System.out.println("Successfully connected to database!");
        } catch(SQLException error) {
            System.out.println("Unable to connect to database" + error);
        }
    }

    public void insertCar(Integer carOwner,
                          String brand,
                          String model,
                          Integer yearOfFabrication,
                          Float engineSize,
                          String fuel,
                          Integer horsePower) {
        boolean done = false;
        String query = "INSERT INTO cars_in_service(Car_Owner, Brand_Name, Model_Name, Year_of_fabrication, Engine_Size, Fuel, Horse_Power) "
                         + "VALUES(" + carOwner.toString() + ", '" + brand + "', '" + model + "', " + yearOfFabrication.toString() + ", " + engineSize.toString() + ", '" + fuel + "'," + horsePower.toString() + ");";
;
        try {
            done = statement.execute(query);
        } catch (SQLException e) {
            System.out.println("Error in inserting car into database: " + e);
        }

        System.out.println(done);
    }

    public void updateCar(Integer carOwner,
                          String brand,
                          String model,
                          Integer assignedService) {
        String query = "UPDATE cars_in_service " +
                        "SET Assigned_Service_Location = " + assignedService.toString() +
                        " WHERE Car_Owner = " + carOwner.toString() + " AND Brand_Name = '" + brand + "' AND Model_Name = '" + model + "';";
        try {
            statement.execute(query);
        } catch(SQLException error) {
            System.out.println("Error in updating car: " + error);
        }
    }

    public void deleteCar(Integer carOwner,
                          Integer assignedService,
                          String brand,
                          String model) {
        String query = "DELETE FROM cars_in_service " +
                        "WHERE Car_Owner = " + carOwner.toString() + " AND Assigned_Service_Location = " + assignedService.toString() + " AND Brand_Name = '" + brand + "' AND Model_Name = '" + model + "';";
        String query1 = "DELETE FROM assigned_mechanics_to_cars A\n" +
                "WHERE A.CarID = (SELECT CarID FROM cars_in_service WHERE Brand_Name = '"+brand+"' AND Model_Name = '"+model+"' AND Car_Owner = "+carOwner.toString()+" AND Assigned_Service_Location = "+assignedService.toString()+")";
        String query2 = "DELETE FROM car_malfunctions A\n" +
                "WHERE A.CarID = (SELECT CarID FROM cars_in_service WHERE Brand_Name = '"+brand+"' AND Model_Name = '"+model+"' AND Car_Owner = "+carOwner.toString()+" AND Assigned_Service_Location = "+assignedService.toString()+")";
        String query3 = "DELETE FROM schedules A\n" +
                "WHERE A.Assigned_Car = (SELECT CarID FROM cars_in_service WHERE Brand_Name = '"+brand+"' AND Model_Name = '"+model+"' AND Car_Owner = "+carOwner.toString()+" AND Assigned_Service_Location = "+assignedService.toString()+")";

        try {
            statement.execute(query1);
            statement.execute(query2);
            statement.execute(query3);
            statement.execute(query);
        } catch(SQLException error) {
            System.out.println("Error in deleting car: " + error);
        }
    }
}
