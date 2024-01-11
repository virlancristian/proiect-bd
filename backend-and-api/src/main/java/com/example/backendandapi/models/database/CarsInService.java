package com.example.backendandapi.models.database;

public class CarsInService {
    int carOwner;
    String brand;
    String model;
    int yearOfFabrication;
    float engineSize;
    String fuel;
    int horsePower;

    public CarsInService(int carOwner, String brand, String model, int yearOfFabrication, float engineSize, String fuel, int horsePower) {
        this.carOwner = carOwner;
        this.brand = brand;
        this.model = model;
        this.yearOfFabrication = yearOfFabrication;
        this.engineSize = engineSize;
        this.fuel = fuel;
        this.horsePower = horsePower;
    }

    public int getCarOwner() {
        return carOwner;
    }

    public void setCarOwner(int carOwner) {
        this.carOwner = carOwner;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYearOfFabrication() {
        return yearOfFabrication;
    }

    public void setYearOfFabrication(int yearOfFabrication) {
        this.yearOfFabrication = yearOfFabrication;
    }

    public float getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(float engineSize) {
        this.engineSize = engineSize;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    @Override
    public String toString() {
        return "CarsInService{" +
                "carOwner=" + carOwner +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", yearOfFabrication=" + yearOfFabrication +
                ", engineSize=" + engineSize +
                ", fuel='" + fuel + '\'' +
                ", horsePower=" + horsePower +
                '}';
    }
}
