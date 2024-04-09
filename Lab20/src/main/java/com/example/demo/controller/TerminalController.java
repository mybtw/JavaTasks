package com.example.demo.controller;

import com.example.demo.dto.PinRequest;
import com.example.demo.dto.TransactionRequest;
import com.example.demo.exception.AccountIsLockedException;
import com.example.demo.exception.InsufficientFundsException;
import com.example.demo.exception.InvalidPinException;
import com.example.demo.terminal.Terminal;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/terminal")
public class TerminalController {

    private final Terminal terminal;

    @Autowired
    public TerminalController(Terminal terminal) {
        this.terminal = terminal;
    }

    @PostMapping("/enterPin")
    public ResponseEntity<String> enterPin(@RequestBody PinRequest pinRequest, HttpSession session) {
        try {
            terminal.enterPin(pinRequest.getPin());
            session.setAttribute("isAuthenticated", true);
            return ResponseEntity.ok("PIN is correct.");
        } catch (InvalidPinException | AccountIsLockedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/balance")
    public ResponseEntity<String> getBalance(HttpSession session) {
        if (isAuthenticated(session)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Please enter a valid PIN.");
        }
        try {
            double balance = terminal.checkBalance();
            return ResponseEntity.ok("Your balance is: " + balance);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("An error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody TransactionRequest request, HttpSession session) {
        if (isAuthenticated(session)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Please enter a valid PIN.");
        }
        try {
            terminal.withdraw(request.getAmount());
            return ResponseEntity.ok("Withdrawal successful.");
        } catch (InsufficientFundsException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody TransactionRequest request, HttpSession session) {
        if (isAuthenticated(session)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Please enter a valid PIN.");
        }
        try {
            terminal.deposit(request.getAmount());
            return ResponseEntity.ok("Deposit successful.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private boolean isAuthenticated(HttpSession session) {
        Boolean isAuthenticated = (Boolean) session.getAttribute("isAuthenticated");
        return !Boolean.TRUE.equals(isAuthenticated);
    }

}
