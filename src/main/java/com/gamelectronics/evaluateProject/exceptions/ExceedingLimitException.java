package com.gamelectronics.evaluateProject.exceptions;

public class ExceedingLimitException extends Exception {
    public ExceedingLimitException() {
        super("You have exceeded the unit limit for this building.");
    }
}
