package com.example.backend.models.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ServiceLocations {
    @Id
    Integer ServiceLocationID;
    String Name;
    String Street_Address;
    Integer Street_Number;

    public ServiceLocations() {}

    public ServiceLocations(Integer serviceLocationID, String name, String street_Address, Integer street_Number) {
        ServiceLocationID = serviceLocationID;
        Name = name;
        Street_Address = street_Address;
        Street_Number = street_Number;
    }

    public Integer getServiceLocationID() {
        return ServiceLocationID;
    }

    public void setServiceLocationID(Integer serviceLocationID) {
        ServiceLocationID = serviceLocationID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStreet_Address() {
        return Street_Address;
    }

    public void setStreet_Address(String street_Address) {
        Street_Address = street_Address;
    }

    public Integer getStreet_Number() {
        return Street_Number;
    }

    public void setStreet_Number(Integer street_Number) {
        Street_Number = street_Number;
    }

    @Override
    public String toString() {
        return "ServiceLocations{" +
                "ServiceLocationID=" + ServiceLocationID +
                ", Name='" + Name + '\'' +
                ", Street_Address='" + Street_Address + '\'' +
                ", Street_Number=" + Street_Number +
                '}';
    }
}
