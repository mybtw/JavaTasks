package org.example.threadpool;

import java.util.LinkedList;
import java.util.Queue;

public class FixedThreadPool implements ThreadPool {
    private final Queue<Runnable> tasks = new LinkedList<>();
    private final Thread[] threads;
    private volatile boolean isRunning = false;
    private volatile boolean isTerminated = false;

    public FixedThreadPool(int numberOfThreads) {
        if (numberOfThreads < 1) {
            throw new IllegalArgumentException("Должен быть хотя бы 1 поток");
        }
        threads = new Thread[numberOfThreads];
        createThreads(numberOfThreads);
    }

    private void createThreads(int numberOfThreads) {
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(() -> {
                while (isRunning || !tasks.isEmpty()) {
                    Runnable task;
                    synchronized (tasks) {
                        while (tasks.isEmpty() && isRunning) {
                            try {
                                tasks.wait();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                        task = tasks.poll();
                    }
                    try {
                        if (task != null) {
                            task.run();
                        }
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                }
            });
        }
    }

    @Override
    public void start() {
        isRunning = true;
        isTerminated = false;
        for (Thread thread : threads) {
            thread.start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (!isTerminated) {
            synchronized (tasks) {
                tasks.add(runnable);
                tasks.notify();
            }
        }
    }

    public void shutdown() {
        isRunning = false;
        isTerminated = true;
        synchronized (tasks) {
            tasks.notifyAll();
        }
    }

    public void awaitTermination() throws InterruptedException {
        for (Thread thread : threads) {
            thread.join(); // Дожидаемся завершения каждого потока
        }
    }


}
