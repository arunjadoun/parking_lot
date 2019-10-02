package com.gojek.parkinglot.validator;

import com.gojek.parkinglot.enums.Commands;
import com.gojek.parkinglot.request.Command;

public class CreateLotCommandValidator extends CommandValidator {


    public CreateLotCommandValidator(Command command) {
        super(command);
    }

    public boolean validate() {
        if (command == null || command.getArguments() == null || command.getArguments().size()!=1)
            return false;
        if (!command.getCommand().equals(Commands.CREATE_PARKING_LOT))
            return false;
        if (command.getArguments().isEmpty() || !ValidationUtil.isInteger(command.getArguments().get(0)))
            return false;
        if(Integer.parseInt(command.getArguments().get(0))<=0)
            return false;
        return true;

    }
}
