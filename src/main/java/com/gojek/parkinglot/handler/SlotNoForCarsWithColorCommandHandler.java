package com.gojek.parkinglot.handler;

import com.gojek.parkinglot.Parkings;
import com.gojek.parkinglot.request.Command;

public class SlotNoForCarsWithColorCommandHandler extends CommandHandler {


    public SlotNoForCarsWithColorCommandHandler(Command command) {
        super(command);
    }

    @Override
    public void handle() {
        boolean isValidCommand = validate();
        if (isValidCommand) {
            Parkings.showSlotNosForParkedVehicleWithColor(command.getArguments().get(0));
        }
    }

}
