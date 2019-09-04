package com.gamelectronics.evaluateProject.exceptions;

public class DuplicateElementException extends Exception{
    public DuplicateElementException(){
        super("this element was duplicated.");
    }
}
