package org.example.threadpool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ScalableThreadPool implements ThreadPool {
    private final Queue<Runnable> tasks = new LinkedList<>();
    private final List<Thread> threads = new ArrayList<>();
    private final int min;
    private final int max;
    private volatile boolean isRunning = false;
    private volatile boolean isTerminated = false;

    public ScalableThreadPool(int min, int max) {
        int minThreads = Math.min(min, max);
        int maxThreads = Math.max(min, max);
        if (minThreads < 1) {
            throw new IllegalArgumentException("Должен быть хотя бы 1 поток");
        }
        this.min = minThreads;
        this.max = maxThreads;
    }

    @Override
    public void start() {
        isRunning = true;
        isTerminated = false;
        for (int i = 0; i < min; i++) {
            createThread();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (!isTerminated) {
            synchronized (tasks) {
                tasks.add(runnable);
                if (threads.size() < max && tasks.size() > 1) {
                    createThread();
                }
                tasks.notify();
            }
        }
    }

    private void createThread() {
        Thread thread = new Thread(() -> {
            Runnable task;
            while (isRunning || !tasks.isEmpty()) {
                synchronized (tasks) {
                    while (tasks.isEmpty() && isRunning) {
                        try {
                            if (threads.size() > min) {
                                threads.remove(Thread.currentThread());
                                return;
                            }
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
        threads.add(thread);
        thread.start();
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
            thread.join();
        }
    }

}