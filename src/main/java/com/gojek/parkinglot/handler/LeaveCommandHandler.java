package com.gojek.parkinglot.handler;

import com.gojek.parkinglot.request.Command;
import com.gojek.parkinglot.request.Lead;

public class LeaveCommandHandler extends CommandHandler {


    public LeaveCommandHandler(Command Command) {
        super(Command);
    }

    @Override
    public void handle() {
        boolean isValidCommand = validate();
        if (isValidCommand) {
            Lead lead = Lead.createUnParkingLead(command.getArguments().get(0));
            if (lead != null && lead.getParkableVehicle() != null)
                lead.getParkableVehicle().unPark(lead);
        }
    }

}
