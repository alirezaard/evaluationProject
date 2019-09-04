package com.gamelectronics.evaluateProject.utils;

import com.gamelectronics.evaluateProject.exceptions.DuplicateElementException;
import com.gamelectronics.evaluateProject.exceptions.ExceedingLimitException;
import com.gamelectronics.evaluateProject.exceptions.ResourceNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public RestResponse<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return RestResponse.error(101, "this resource not found. please check your request./your request: "
                + request.getContextPath(), ex.getCause());
    }

    @ExceptionHandler(ExceedingLimitException.class)
    public RestResponse<?> exceedingLimitException(ResourceNotFoundException ex, WebRequest request) {
        return RestResponse.error(102, "You have exceeded the unit limit for this building./ your request: "
                + request.getContextPath(), ex.getCause());

    }

    @ExceptionHandler(DuplicateElementException.class)
    public RestResponse<?> duplicateElementException(ResourceNotFoundException ex, WebRequest request) {
        return RestResponse.error(103, "this element was duplicated./ your request: "
                + request.getContextPath(), ex.getCause());

    }

    @ExceptionHandler(Exception.class)
    public RestResponse<?> myGlobalExceptionHandler(Exception ex, WebRequest request) {
        return RestResponse.error(104, "an error has been occurred./ message: "
                + ex.getMessage() + "/ your request: " + request.getContextPath(), ex.getCause());
    }
}