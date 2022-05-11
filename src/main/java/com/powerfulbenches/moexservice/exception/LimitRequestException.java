package com.powerfulbenches.moexservice.exception;

public class LimitRequestException extends RuntimeException {
    public LimitRequestException(String message) {
        super(message);
    }
}
