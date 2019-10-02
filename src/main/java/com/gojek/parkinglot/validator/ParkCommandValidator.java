package com.gojek.parkinglot.validator;

import com.gojek.parkinglot.enums.Commands;
import com.gojek.parkinglot.request.Command;

public class ParkCommandValidator extends CommandValidator {
    public ParkCommandValidator(Command command) {
        super(command);
    }

    public boolean validate() {
        if (command == null || command.getArguments() == null)
            return false;
        if (!command.getCommand().equals(Commands.PARK) || command.getArguments().size() != 2)
            return false;
        if (command.getArguments().isEmpty() || command.getArguments().get(0).isEmpty())
            return false;
        return true;
    }
}
