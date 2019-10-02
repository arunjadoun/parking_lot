package com.gojek.parkinglot.handler;

import com.gojek.parkinglot.Parkings;
import com.gojek.parkinglot.request.Command;

public class StatusCommandHandler extends CommandHandler {

    public StatusCommandHandler(Command command) {
        super(command);
    }

    @Override
    public void handle() {
        boolean isValidCommand = validate();
        if (isValidCommand) {
            Parkings.showParkedDetails();
        }
    }
}
