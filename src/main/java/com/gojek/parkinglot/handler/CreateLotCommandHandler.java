package com.gojek.parkinglot.handler;

import com.gojek.parkinglot.ParkingLot;
import com.gojek.parkinglot.abstractions.Handler;
import com.gojek.parkinglot.request.Command;

public class CreateLotCommandHandler extends CommandHandler implements Handler {


    public CreateLotCommandHandler(Command command) {
        super(command);
    }

    public void handle() {
        boolean isValidCommand = validate();
        if (isValidCommand) {
            ParkingLot.create(Integer.parseInt(command.getArguments().get(0)));
        }
    }

}