package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PinRequest {
    private String pin;

    public void setPin(String pin) {
        this.pin = pin;
    }
}
