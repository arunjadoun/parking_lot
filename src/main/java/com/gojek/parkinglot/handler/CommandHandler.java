package com.gojek.parkinglot.handler;

import com.gojek.parkinglot.abstractions.Handler;
import com.gojek.parkinglot.request.Command;
import com.gojek.parkinglot.validator.CommandValidator;
import com.gojek.parkinglot.validator.CommandValidatorFactory;

public abstract class CommandHandler extends CommandValidator implements Handler {
    protected final Command command;

    public CommandHandler(Command command) {
        super(command);
        this.command = command;
    }

    public boolean validate() {
        if (command == null) {
            return false;
        }
        CommandValidator commandValidator = CommandValidatorFactory.getCommandValidator(command);
        if (commandValidator == null) {
            return false;
        }
        return commandValidator.validate();
    }
}
