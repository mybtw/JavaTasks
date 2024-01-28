package org.example.message;

public interface MessageHandler {
    void printInfo(String message);
    void printError(String message);
    void printWarning(String message);
}
