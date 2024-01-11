package com.example.backend.models.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Client {
    @Id
    Integer ClientID;
    String First_Name;
    String Last_Name;
    String Email;
    String Phone_Number;
    String Street_Address;
    Integer Street_Number;
    String Flat_Number;
    Integer Apartment;
    String City;

    public Client() {}

    public Client(Integer clientID, String first_Name, String last_Name, String email, String phone_Number, String street_Address, Integer street_Number, String flat_Number, Integer apartment, String city) {
        ClientID = clientID;
        First_Name = first_Name;
        Last_Name = last_Name;
        Email = email;
        Phone_Number = phone_Number;
        Street_Address = street_Address;
        Street_Number = street_Number;
        Flat_Number = flat_Number;
        Apartment = apartment;
        City = city;
    }

    public Integer getClientID() {
        return ClientID;
    }

    public void setClientID(Integer clientID) {
        ClientID = clientID;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone_Number() {
        return Phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        Phone_Number = phone_Number;
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

    public String getFlat_Number() {
        return Flat_Number;
    }

    public void setFlat_Number(String flat_Number) {
        Flat_Number = flat_Number;
    }

    public Integer getApartment() {
        return Apartment;
    }

    public void setApartment(Integer apartment) {
        Apartment = apartment;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    @Override
    public String toString() {
        return "Client{" +
                "ClientID=" + ClientID +
                ", First_Name='" + First_Name + '\'' +
                ", Last_Name='" + Last_Name + '\'' +
                ", Email='" + Email + '\'' +
                ", Phone_Number='" + Phone_Number + '\'' +
                ", Street_Address='" + Street_Address + '\'' +
                ", Street_Number=" + Street_Number +
                ", Flat_Number='" + Flat_Number + '\'' +
                ", Apartment=" + Apartment +
                ", City='" + City + '\'' +
                '}';
    }
}
