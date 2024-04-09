package com.example.demo.terminal.server;

import com.example.demo.exception.InsufficientFundsException;
import org.springframework.stereotype.Service;

@Service
public class TerminalServer {
    private double balance;

    public TerminalServer() {
        this.balance = 0;
    }

    public TerminalServer(double balance) {
        this.balance = balance;
    }

    public double checkBalance() {
        return balance;
    }

    public void deposit(int amount) throws IllegalArgumentException {
        if (amount % 100 != 0) {
            throw new IllegalArgumentException("Amount must be a multiple of 100.");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        balance += amount;
    }

    public void withdraw(int amount) throws  InsufficientFundsException, IllegalArgumentException {
        if (amount % 100 != 0) {
            throw new IllegalArgumentException("Amount must be a multiple of 100.");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        if (balance < amount) {
            throw new InsufficientFundsException("Insufficient funds for the transaction.");
        }
        balance -= amount;
    }


}
