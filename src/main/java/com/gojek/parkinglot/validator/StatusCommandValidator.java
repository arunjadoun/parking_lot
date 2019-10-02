package com.gojek.parkinglot.validator;

import com.gojek.parkinglot.enums.Commands;
import com.gojek.parkinglot.request.Command;

public class StatusCommandValidator extends CommandValidator {
    public StatusCommandValidator(Command command) {
        super(command);
    }

    public boolean validate() {
        if (command == null || command.getArguments() == null)
            return false;
        if (!command.getCommand().equals(Commands.STATUS))
            return false;
        return true;
    }
}
