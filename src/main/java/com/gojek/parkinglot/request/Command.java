package com.gojek.parkinglot.request;

import com.gojek.parkinglot.enums.Commands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Command {

    private final Commands command;
    private final List<String> arguments;
    private final static String BY_SPACE = " ";

    public Command(Commands command, String[] arguments) {
        this.command = command;
        this.arguments = Arrays.asList(arguments);
    }

    public Command(Commands command) {
        this.command = command;
        this.arguments = Collections.emptyList();
    }
    public static Command getCommandWrapperFromString(String commandStr) {
        commandStr = commandStr.replaceAll("\n", "");
        String[] commandArr = commandStr.split(BY_SPACE);
        try {
            Commands command = Commands.fromValue(commandArr[0]);
            if (commandArr.length > 1) {
                String[] commandArgs = Arrays.copyOfRange(commandArr, 1, commandArr.length);
                return new Command(command, commandArgs);
            }
            return new Command(command);
        } catch (IllegalArgumentException e) {
            // invalid input
        }
        return null;
    }

    public Commands getCommand() {
        return command;
    }

    public List<String> getArguments() {
        return arguments;
    }

}
