package com.gojek.parkinglot.abstractions;

import com.gojek.parkinglot.model.ParkingTicket;
import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.request.Lead;

public interface Parkable {
    /**
     * Takes a parkingRequest object and upon successful park returns {@link Lead}
     * @param parkingRequest parking request object encapsulate
     * @return
     */
    ParkingTicket park(Lead parkingRequest);
    void unPark(Lead parkingRequest);
    Vehicle getVehicle();
    boolean isParked();
}