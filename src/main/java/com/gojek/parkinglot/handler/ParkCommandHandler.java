package com.gojek.parkinglot.handler;

import com.gojek.parkinglot.abstractions.Parkable;
import com.gojek.parkinglot.enums.LeadType;
import com.gojek.parkinglot.model.Car;
import com.gojek.parkinglot.request.Command;
import com.gojek.parkinglot.request.Lead;

public class ParkCommandHandler extends CommandHandler {


    public ParkCommandHandler(Command command) {
        super(command);
    }

    @Override
    public void handle() {
        boolean isValidCommand = validate();
        if (isValidCommand) {
            Parkable vehicle = new Car(command.getArguments().get(0), command.getArguments().get(1));
            Lead lead = Lead.createParkingLead(vehicle, LeadType.PARK);
            lead.getParkableVehicle().park(lead);
        }
    }

}
