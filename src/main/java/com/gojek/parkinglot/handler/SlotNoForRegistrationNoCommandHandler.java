package com.gojek.parkinglot.handler;

import com.gojek.parkinglot.Parkings;
import com.gojek.parkinglot.request.Command;

public class SlotNoForRegistrationNoCommandHandler extends CommandHandler {

    public SlotNoForRegistrationNoCommandHandler(Command command) {
        super(command);
    }

    @Override
    public void handle() {
        boolean isValidCommand = validate();
        if (isValidCommand) {
            Parkings.showSlotNoForParkedVehicleWithRegNo(command.getArguments().get(0));
        }
    }

}
