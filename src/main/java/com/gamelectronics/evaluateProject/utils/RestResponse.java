package com.gamelectronics.evaluateProject.utils;

import com.gamelectronics.evaluateProject.domain.dtos.ExceptionDto;

public class RestResponse<T> {

    private String message;
    private T content;


    private RestResponse() {
    }

    public static RestResponse<Void> ok() {
        return ok(null);
    }

    public static <T> RestResponse<T> ok(T content) {
        RestResponse<T> response = new RestResponse<>();
        response.content = content;
        if (content instanceof ExceptionDto)
            response.message = "ERROR";
        else
            response.message = "OK";
        return response;
    }

    public T getContent() {
        return content;
    }

    public String getMessage() {
        return message;
    }
}
