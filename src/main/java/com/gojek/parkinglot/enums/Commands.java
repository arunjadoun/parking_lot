package com.gojek.parkinglot.enums;

public enum Commands {

    CREATE_PARKING_LOT("create_parking_lot"),
    PARK("park"),
    LEAVE("leave"),
    STATUS("status"),
    REGISTRATION_NOS_OF_CARS_WITH_COLOR("registration_numbers_for_cars_with_colour"),
    SLOT_NOS_FOR_CARS_WITH_COLOR("slot_numbers_for_cars_with_colour"),
    SLOT_NO_FOR_REGISTRATION_NO("slot_number_for_registration_number"),
    EXIT("exit");

    private String value;

    private Commands(String value) {
        this.value = value;
    }

    public static Commands fromValue(String value) {
        if (value != null && !value.equals("")) {
            Commands commandFromString = null;
            Commands[] allPermissibleCommands = values();
            for (Commands command : allPermissibleCommands) {
                if (command.value.equals(value))
                    commandFromString = command;
            }

            if (commandFromString == null) {
                throw new IllegalArgumentException("Invalid Command String Value: " + value);
            }
            return commandFromString;

        } else {
            throw new IllegalArgumentException("Command Value can't be null or empty");
        }
    }

    public String toString() {
        return this.value;
    }

}
