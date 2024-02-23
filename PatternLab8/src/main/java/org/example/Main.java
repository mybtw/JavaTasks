package org.example;

import org.example.message.ConcreteMessageHandlerFactory;
import org.example.message.ConsoleMessageHandler;
import org.example.message.MessageHandler;
import org.example.message.MessageHandlerFactory;
import org.example.proxy.cache.CacheProxy;
import org.example.service.Service;
import org.example.service.ServiceImpl;

import java.time.Instant;
import java.util.Date;

public class Main {
    private static final String rootDir = "C:\\Users\\astaf\\IdeaProjects\\JavaTasks\\Lab8\\cacheFiles";

    public static void main(String[] args) {
        MessageHandlerFactory factory = new ConcreteMessageHandlerFactory();
        MessageHandler consoleHandler = factory.createConsoleMessageHandler();
        MessageHandler fileHandler = factory.createFileMessageHandler("messages.log");
        try {
            CacheProxy cacheProxy = new CacheProxy(rootDir);
            Service workService = cacheProxy.cache(new ServiceImpl());
            Service runService = cacheProxy.cache(new ServiceImpl());
            consoleHandler.printInfo("=================== TEST 1 ===================");
            testWorkMethod(workService, fileHandler);
            consoleHandler.printInfo("=================== TEST 2 ===================");
            testRunMethod(runService, fileHandler);
        } catch (InterruptedException e) {
            consoleHandler.printError("Произошла ошибка: Этот поток был прерван");
        } catch (Exception e) {
            consoleHandler.printError("Произошла ошибка: " + e.getMessage());
        }
    }

    public static void testWorkMethod(Service service, MessageHandler messageHandler) throws InterruptedException {
        messageHandler.printInfo("Выполнение первого метода:");
        long startTime = System.nanoTime();
        service.work("fine", 5);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        messageHandler.printInfo("Время выполнения функции: " + duration + " наносекунд");

        messageHandler.printInfo("Выполнение второго метода:");
        startTime = System.nanoTime();
        var lst = service.work("fine", 5);
        endTime = System.nanoTime();
        for (String s : lst) {
            System.out.print(s + " ");
        }
        System.out.println();
        duration = endTime - startTime;
        messageHandler.printInfo("Время выполнения функции: " + duration + " наносекунд");

        messageHandler.printInfo("Выполнение третьего метода:");
        startTime = System.nanoTime();
        service.work("fine1", 228);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        messageHandler.printInfo("Время выполнения функции: " + duration + " наносекунд");
    }

    public static void testRunMethod(Service service, MessageHandler messageHandler) throws InterruptedException {
        messageHandler.printInfo("Выполнение первого метода:");
        long startTime = System.nanoTime();
        service.run("fine", 5.9, Date.from(Instant.ofEpochMilli(82448348)));
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        messageHandler.printInfo("Время выполнения функции: " + duration + " наносекунд");

        messageHandler.printInfo("Выполнение второго метода:");
        startTime = System.nanoTime();
        var lst = service.run("fine", 5.9, Date.from(Instant.ofEpochMilli(82448348)));
        endTime = System.nanoTime();
        for (String s : lst) {
            System.out.print(s + " ");
        }
        System.out.println();
        duration = endTime - startTime;
        messageHandler.printInfo("Время выполнения функции: " + duration + " наносекунд");

        messageHandler.printInfo("Выполнение третьего метода:");
        startTime = System.nanoTime();
        service.run("fine2", 5.96, Date.from(Instant.now()));
        endTime = System.nanoTime();
        duration = endTime - startTime;
        messageHandler.printInfo("Время выполнения функции: " + duration + " наносекунд");
    }
}