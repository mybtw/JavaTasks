package org.example.message;

public interface MessageHandlerFactory {
    MessageHandler createConsoleMessageHandler();
    MessageHandler createFileMessageHandler(String filename);
}
