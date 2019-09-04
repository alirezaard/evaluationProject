package com.gamelectronics.evaluateProject.utils;

import com.gamelectronics.evaluateProject.domain.dtos.ExceptionDto;
import com.gamelectronics.evaluateProject.exceptions.DuplicateElementException;
import com.gamelectronics.evaluateProject.exceptions.ExceedingLimitException;
import com.gamelectronics.evaluateProject.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public RestResponse<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return RestResponse.ok(new ExceptionDto("this resource not found. please check your request./your request: "
                + ((ServletWebRequest) request).getRequest().getRequestURL()+" ]", 101));

    }

    @ExceptionHandler(ExceedingLimitException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public RestResponse<ExceptionDto> exceedingLimitException(ExceedingLimitException ex, WebRequest request) {
        return RestResponse.ok(new ExceptionDto("You have exceeded the unit limit for this building.[ your request: "
                + ((ServletWebRequest) request).getRequest().getRequestURL()+" ]", 102));

    }

    @ExceptionHandler(DuplicateElementException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public RestResponse<ExceptionDto> duplicateElementException(DuplicateElementException ex, WebRequest request) {
        return RestResponse.ok(new ExceptionDto("this element was duplicated.[ your request: "
                + ((ServletWebRequest) request).getRequest().getRequestURL()+" ]", 103));


    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public RestResponse<ExceptionDto> myGlobalExceptionHandler(Exception ex, WebRequest request) {
        return RestResponse.ok(new ExceptionDto("an error has been occurred./ message: "
                + ex.getMessage() + "[ your request: " + ((ServletWebRequest) request).getRequest().getRequestURL()+" ]", 104));
    }
}