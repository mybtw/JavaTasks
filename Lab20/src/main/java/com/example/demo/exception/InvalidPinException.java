package com.example.demo.exception;

public class InvalidPinException extends Exception {
    public InvalidPinException(String message) {
        super(message);
    }
}
