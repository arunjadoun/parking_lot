package com.gojek.parkinglot.handler;


import com.gojek.parkinglot.request.Command;

public class InvalidCommandHandler extends CommandHandler {

    public InvalidCommandHandler(Command command) {
        super(command);
    }

    @Override
    public void handle() {
        // Invalid command
        System.out.println("Invalid command");
    }

    @Override
    public boolean validate() {
        return false;
    }
}
