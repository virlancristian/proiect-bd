package com.example.backend.models.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Malfunction {
    @Id
    Integer MalfunctionID;
    String Malfunction;
    String Damaged_Component;

    public Malfunction() {}

    public Malfunction(Integer malfunctionID, String malfunction, String damaged_Component) {
        MalfunctionID = malfunctionID;
        Malfunction = malfunction;
        Damaged_Component = damaged_Component;
    }

    public Integer getMalfunctionID() {
        return MalfunctionID;
    }

    public void setMalfunctionID(Integer malfunctionID) {
        MalfunctionID = malfunctionID;
    }

    public String getMalfunction() {
        return Malfunction;
    }

    public void setMalfunction(String malfunction) {
        Malfunction = malfunction;
    }

    public String getDamaged_Component() {
        return Damaged_Component;
    }

    public void setDamaged_Component(String damaged_Component) {
        Damaged_Component = damaged_Component;
    }

    @Override
    public String toString() {
        return "Malfunction{" +
                "MalfunctionID=" + MalfunctionID +
                ", Malfunction='" + Malfunction + '\'' +
                ", Damaged_Component='" + Damaged_Component + '\'' +
                '}';
    }
}
