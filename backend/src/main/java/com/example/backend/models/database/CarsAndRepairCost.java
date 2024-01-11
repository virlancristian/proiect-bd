package com.example.backend.models.database;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class CarsAndRepairCost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer CarID;
    int Car_Owner;
    Integer Assigned_Service_Location;
    String Brand_Name;
    String Model_Name;
    int Year_of_fabrication;
    float Engine_Size;
    String fuel;
    int Horse_Power;
    Integer RepairCost;

    public CarsAndRepairCost() {
    }

    public CarsAndRepairCost(int id, int carOwner, String brand_Name, String model_Name, int year_of_fabrication, float engine_Size, String fuel, int horse_Power, Integer Assigned_Service_Location, Integer RepairCost) {
        this.CarID = id;
        Car_Owner = carOwner;
        Brand_Name = brand_Name;
        Model_Name = model_Name;
        Year_of_fabrication = year_of_fabrication;
        Engine_Size = engine_Size;
        this.fuel = fuel;
        Horse_Power = horse_Power;
        this.Assigned_Service_Location = Assigned_Service_Location;
        this.RepairCost = RepairCost;
    }

    public int getCarID() {
        return CarID;
    }

    public void setCarID(int carID) {
        this.CarID = carID;
    }

    public int getCar_Owner() {
        return Car_Owner;
    }

    public void setCar_Owner(int car_Owner) {
        Car_Owner = car_Owner;
    }

    public String getBrand_Name() {
        return Brand_Name;
    }

    public void setBrand_Name(String brand_Name) {
        Brand_Name = brand_Name;
    }

    public String getModel_Name() {
        return Model_Name;
    }

    public void setModel_Name(String model_Name) {
        Model_Name = model_Name;
    }

    public int getYear_of_fabrication() {
        return Year_of_fabrication;
    }

    public void setYear_of_fabrication(int year_of_fabrication) {
        Year_of_fabrication = year_of_fabrication;
    }

    public float getEngine_Size() {
        return Engine_Size;
    }

    public void setEngine_Size(float engine_Size) {
        Engine_Size = engine_Size;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public int getHorse_Power() {
        return Horse_Power;
    }

    public void setHorse_Power(int horse_Power) {
        Horse_Power = horse_Power;
    }

    public Integer getAssigned_Service_Location() {
        return Assigned_Service_Location;
    }

    public void setAssigned_Service_Location(Integer assigned_Service_Location) {
        Assigned_Service_Location = assigned_Service_Location;
    }

    public Integer getRepairCost() {
        return RepairCost;
    }

    public void setRepairCost(Integer repairCost) {
        RepairCost = repairCost;
    }

    @Override
    public String toString() {
        return "CarsInService{" +
                "id=" + CarID +
                ", CarOwner=" + Car_Owner +
                ", Brand_Name='" + Brand_Name + '\'' +
                ", Model_Name='" + Model_Name + '\'' +
                ", Year_of_fabrication=" + Year_of_fabrication +
                ", Engine_Size=" + Engine_Size +
                ", fuel='" + fuel + '\'' +
                ", Horse_Power=" + Horse_Power +
                ", RepairCost=" + RepairCost +
                '}';
    }
}
