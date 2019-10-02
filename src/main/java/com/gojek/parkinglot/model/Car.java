package com.gojek.parkinglot.model;

import com.gojek.parkinglot.ParkingLot;
import com.gojek.parkinglot.abstractions.Parkable;
import com.gojek.parkinglot.request.Lead;

public class Car extends Vehicle implements Parkable {

    public Car(String registrationNumber, String color) {
        super(registrationNumber, color);
    }

    public ParkingTicket park(Lead lead) {
        return ParkingLot.park(lead);
    }

    public void unPark(Lead lead) {
        ParkingLot.unPark(lead);
    }

    public Vehicle getVehicle() {
        return this;
    }

    @Override
    public boolean isParked() {
        return ParkingTicket.ticketMap != null && ParkingTicket.ticketMap.get(this.getRegistrationNumber()) != null;
    }

    public String getColor() {
        return super.getColor();
    }

    public String getRegistrationNumber() {
        return super.getRegistrationNumber();
    }


}