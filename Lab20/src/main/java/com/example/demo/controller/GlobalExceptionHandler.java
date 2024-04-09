package com.example.demo.controller;

import com.example.demo.exception.AccountIsLockedException;
import com.example.demo.exception.InsufficientFundsException;
import com.example.demo.exception.InvalidPinException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {InvalidPinException.class, AccountIsLockedException.class, InsufficientFundsException.class, IllegalArgumentException.class})
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
