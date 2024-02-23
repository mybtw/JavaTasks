package org.example.message;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileMessageHandler implements MessageHandler {
    private final String filename;

    public FileMessageHandler(String filename) {
        this.filename = filename;
    }

    @Override
    public void printInfo(String message) {
        writeToFile("INFO: " + message);
    }

    @Override
    public void printError(String message) {
        writeToFile("ERROR: " + message);
    }

    @Override
    public void printWarning(String message) {
        writeToFile("WARNING: " + message);
    }

    private void writeToFile(String message) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filename, true))) {
            out.println(message);
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}
