package com.gojek.parkinglot.validator;

import com.gojek.parkinglot.enums.Commands;
import com.gojek.parkinglot.request.Command;

public class CommandValidatorFactory {

    private static CreateLotCommandValidator CREATE_LOT_COMMAND_VALIDATOR;
    private static ParkCommandValidator PARK_COMMAND_VALIDATOR;
    private static LeaveCommandValidator LEAVE_COMMAND_VALIDATOR;
    private static StatusCommandValidator STATUS_COMMAND_VALIDATOR;
    private static SlotNoForRegistrationNoValidator SLOT_NO_FOR_REGISTRATION_NO_VALIDATOR;
    private static SlotNosForCarsWithColorValidator SLOT_NOS_FOR_CARS_WITH_COLOR_VALIDATOR;
    private static RegistrationNosOfCarsByColorCommandValidator REGISTRATION_NOS_OF_CARS_BY_COLOR_COMMAND_VALIDATOR;
    private static ExitCommandValidator EXIT_COMMAND_VALIDATOR;

    public static CommandValidator getCommandValidator(Command command) {
        switch (command.getCommand()) {
            case CREATE_PARKING_LOT:
                    CREATE_LOT_COMMAND_VALIDATOR = new CreateLotCommandValidator(command);
                return CREATE_LOT_COMMAND_VALIDATOR;
            case PARK:
                    PARK_COMMAND_VALIDATOR = new ParkCommandValidator(command);
                return PARK_COMMAND_VALIDATOR;
            case LEAVE:
                    LEAVE_COMMAND_VALIDATOR = new LeaveCommandValidator(command);
                return LEAVE_COMMAND_VALIDATOR;
            case STATUS:
                    STATUS_COMMAND_VALIDATOR = new StatusCommandValidator(command);
                return STATUS_COMMAND_VALIDATOR;
            case SLOT_NO_FOR_REGISTRATION_NO:
                    SLOT_NO_FOR_REGISTRATION_NO_VALIDATOR = new SlotNoForRegistrationNoValidator(command);
                return SLOT_NO_FOR_REGISTRATION_NO_VALIDATOR;
            case SLOT_NOS_FOR_CARS_WITH_COLOR:
                    SLOT_NOS_FOR_CARS_WITH_COLOR_VALIDATOR = new SlotNosForCarsWithColorValidator(command);
                return SLOT_NOS_FOR_CARS_WITH_COLOR_VALIDATOR;
            case REGISTRATION_NOS_OF_CARS_WITH_COLOR:
                    REGISTRATION_NOS_OF_CARS_BY_COLOR_COMMAND_VALIDATOR = new RegistrationNosOfCarsByColorCommandValidator(command);
                return REGISTRATION_NOS_OF_CARS_BY_COLOR_COMMAND_VALIDATOR;
            case EXIT:
                    EXIT_COMMAND_VALIDATOR = new ExitCommandValidator(command);
                return EXIT_COMMAND_VALIDATOR;
            default:
                return null;
        }
    }

}
