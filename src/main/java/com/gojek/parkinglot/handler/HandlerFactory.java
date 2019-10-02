package com.gojek.parkinglot.handler;

import com.gojek.parkinglot.abstractions.Handler;
import com.gojek.parkinglot.enums.Commands;
import com.gojek.parkinglot.request.Command;

public class HandlerFactory {
    public static Handler getCommandHandler(Command commandWrapper) {
        Commands command = commandWrapper.getCommand();
        switch (command) {
            case CREATE_PARKING_LOT:
                return new CreateLotCommandHandler(commandWrapper);
            case PARK:
                return new ParkCommandHandler(commandWrapper);
            case LEAVE:
                return new LeaveCommandHandler(commandWrapper);
            case STATUS:
                return new StatusCommandHandler(commandWrapper);
            case SLOT_NO_FOR_REGISTRATION_NO:
                return new SlotNoForRegistrationNoCommandHandler(commandWrapper);
            case SLOT_NOS_FOR_CARS_WITH_COLOR:
                return new SlotNoForCarsWithColorCommandHandler(commandWrapper);
            case REGISTRATION_NOS_OF_CARS_WITH_COLOR:
                return new RegistrationNosOfCarsByColorCommandHandler(commandWrapper);
            case EXIT:
                return new ExitCommandHandler(commandWrapper);
        }
        return new InvalidCommandHandler(commandWrapper);

    }
}
