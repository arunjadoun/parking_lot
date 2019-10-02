package com.gojek.parkinglot.handler;

import com.gojek.parkinglot.request.Command;

public class ExitCommandHandler extends CommandHandler {


    public ExitCommandHandler(Command command) {
        super(command);
    }

    @Override
    public void handle() {
        boolean isValidCommand = validate();
        if (isValidCommand) {
            System.exit(1);
        }
    }

}
