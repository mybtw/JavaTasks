package com.example.demo.terminal;

import com.example.demo.exception.AccountIsLockedException;
import com.example.demo.exception.InsufficientFundsException;
import com.example.demo.exception.InvalidPinException;
import com.example.demo.pin.PinValidator;
import com.example.demo.terminal.server.TerminalServer;
import org.springframework.stereotype.Service;

@Service
public class TerminalImpl implements Terminal {

    private final TerminalServer server;
    private final PinValidator pinValidator;

    public TerminalImpl(TerminalServer server, String correctPin) {
        this.server = server;
        this.pinValidator = new PinValidator(correctPin);
    }

    @Override
    public double checkBalance() {
        return server.checkBalance();
    }

    @Override
    public void deposit(int amount) throws IllegalArgumentException {
        server.deposit(amount);
    }

    @Override
    public void withdraw(int amount) throws InsufficientFundsException {
        server.withdraw(amount);
    }

    @Override
    public void enterPin(String pin) throws AccountIsLockedException, InvalidPinException {
        pinValidator.validatePin(pin);
    }

}
