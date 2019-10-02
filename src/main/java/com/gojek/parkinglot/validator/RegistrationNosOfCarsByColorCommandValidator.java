package com.gojek.parkinglot.validator;

import com.gojek.parkinglot.enums.Commands;
import com.gojek.parkinglot.request.Command;

public class RegistrationNosOfCarsByColorCommandValidator extends CommandValidator {
    public RegistrationNosOfCarsByColorCommandValidator(Command command) {
        super(command);
    }

    public boolean validate() {
        if (command == null || command.getArguments() == null || command.getArguments().size()!=1)
            return false;
        if (!command.getCommand().equals(Commands.REGISTRATION_NOS_OF_CARS_WITH_COLOR))
            return false;
        if (command.getArguments().isEmpty() || command.getArguments().get(0).isEmpty())
            return false;
        return true;
    }
}
