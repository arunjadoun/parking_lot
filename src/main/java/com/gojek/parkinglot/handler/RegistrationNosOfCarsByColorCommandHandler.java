package com.gojek.parkinglot.handler;

import com.gojek.parkinglot.Parkings;
import com.gojek.parkinglot.request.Command;

public class RegistrationNosOfCarsByColorCommandHandler extends CommandHandler {

    public RegistrationNosOfCarsByColorCommandHandler(Command command) {
        super(command);
    }

    @Override
    public void handle() {
        boolean isValidCommand = validate();
        if (isValidCommand) {
            Parkings.showParkedVehiclesRegNosByColor(command.getArguments().get(0));
        }
    }

}
