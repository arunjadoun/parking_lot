package com.gojek.parkinglot.validator;

public class ValidationUtil {

    public static boolean isInteger(String arg) {
        boolean isValidInteger = false;
        try {
            Integer.parseInt(arg);
            isValidInteger = true;
        } catch (NumberFormatException ex) {
            // arg is not an integer
        }
        return isValidInteger;
    }

}
