package com.example.backend.models.database;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Mechanics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer MechanicID;
    String First_name;
    String Last_Name;
    Integer Working_Location;

    public Mechanics() {
    }

    public Mechanics(Integer mechanicID, String first_name, String last_Name, Integer working_Location) {
        MechanicID = mechanicID;
        First_name = first_name;
        Last_Name = last_Name;
        Working_Location = working_Location;
    }

    public Integer getMechanicID() {
        return MechanicID;
    }

    public void setMechanicID(Integer mechanicID) {
        MechanicID = mechanicID;
    }

    public String getFirst_name() {
        return First_name;
    }

    public void setFirst_name(String first_name) {
        First_name = first_name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public Integer getWorking_Location() {
        return Working_Location;
    }

    public void setWorking_Location(Integer working_Location) {
        Working_Location = working_Location;
    }

    @Override
    public String toString() {
        return "Mechanics{" +
                "MechanicID=" + MechanicID +
                ", First_name='" + First_name + '\'' +
                ", Last_Name='" + Last_Name + '\'' +
                ", Working_Location=" + Working_Location +
                '}';
    }
}
