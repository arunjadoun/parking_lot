package com.gojek.parkinglot.model;

import com.gojek.parkinglot.abstractions.Parkable;

public abstract class Vehicle implements Parkable {

    private final String registrationNumber;
    private final String color;

    public Vehicle(String registrationNumber, String color) {
        this.registrationNumber = registrationNumber;
        this.color = color;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }


    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}