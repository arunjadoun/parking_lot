package com.gojek.parkinglot.validator;

import com.gojek.parkinglot.ParkingLot;
import com.gojek.parkinglot.enums.Commands;
import com.gojek.parkinglot.request.Command;

public class LeaveCommandValidator extends CommandValidator {
    public LeaveCommandValidator(Command command) {
        super(command);
    }

    public boolean validate() {
        if (command == null || command.getArguments() == null || command.getArguments().size()!=1)
            return false;
        if (!command.getCommand().equals(Commands.LEAVE))
            return false;
        if(ParkingLot.getSize()<Integer.parseInt(command.getArguments().get(0))){
            System.out.println("Invalid Slot Number");
            return false;
        }
        return true;
    }
}
