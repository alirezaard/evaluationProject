package com.gamelectronics.evaluateProject.utils;

public class RestResponse<T> {

    private int code;
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
        response.code = 0;
        response.message = "OK";
        return response;
    }

    public static RestResponse error(int code, String message, Object content) {
        RestResponse response = new RestResponse<>();
        response.content = content;
        response.code = code;
        response.message = message;
        return response;
    }

    public T getContent() {
        return content;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
