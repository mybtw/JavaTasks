package org.example.message;

public class ConsoleMessageHandler implements MessageHandler{
    @Override
    public void printInfo(String message) {
        System.out.println(message);
    }

    @Override
    public void printError(String message) {
        System.out.println(message);
    }

    @Override
    public void printWarning(String message) {
        System.out.println(message);
    }
}
