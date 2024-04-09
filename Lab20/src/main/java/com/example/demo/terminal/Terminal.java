package com.example.demo.terminal;

import com.example.demo.exception.AccountIsLockedException;
import com.example.demo.exception.InsufficientFundsException;
import com.example.demo.exception.InvalidPinException;

public interface Terminal {
    double checkBalance();
    void deposit(int amount) throws IllegalArgumentException ;
    void withdraw(int amount) throws InsufficientFundsException;
    void enterPin(String pin) throws AccountIsLockedException, InvalidPinException;
}

