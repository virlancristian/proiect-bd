package com.example.backendandapi.services.dbservice;

import com.example.backendandapi.services.query.read.QueryReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class Dbservice {
    Connection dbConnection;
    Statement statement;

    public Dbservice(@Value("${db.username}") String dbUsername,
                     @Value("${db.password}") String dbPassword) {
        try {
            dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/carservicedb", dbUsername, dbPassword);
            statement = dbConnection.createStatement();

            System.out.println("Successfully connected to database!");
        } catch(SQLException error) {
            System.out.println("Unable to connect to database" + error);
        }
    }

    public ResultSet getItems(String queryFile, String req) {
        String query = new QueryReader(queryFile).getQuery(req);
        ResultSet result;

        try  {
            result = statement.executeQuery(query);

            return result;
        } catch(SQLException error) {
            System.out.println("Unable to retrieve data from table" + error);
        }
        return null;
    }
}
