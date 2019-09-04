package com.gamelectronics.evaluateProject.exceptions;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException() {
        super("This entity was not found in the database.");
    }
}
