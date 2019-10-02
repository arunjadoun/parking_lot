package com.gojek.parkinglot.validator;

import com.gojek.parkinglot.abstractions.Validator;
import com.gojek.parkinglot.request.Command;

public abstract class CommandValidator implements Validator {
    protected Command command;

    public CommandValidator(Command command) {
        this.command = command;
    }
}
