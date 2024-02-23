package org.example.message;

public class ConcreteMessageHandlerFactory implements MessageHandlerFactory{
    @Override
    public MessageHandler createConsoleMessageHandler() {
        return new ConsoleMessageHandler();
    }

    @Override
    public MessageHandler createFileMessageHandler(String filename) {
        return new FileMessageHandler(filename);
    }
}
