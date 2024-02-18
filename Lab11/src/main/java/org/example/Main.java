package org.example;

import org.example.threadpool.FixedThreadPool;
import org.example.threadpool.ScalableThreadPool;
import org.example.threadpool.ThreadPool;

public class Main {
    private static final int numOfTasks = 50;
    private static final int timePerTaskMills = 10;
    private static final int fixedThreadPoolThreads = 5;
    private static final int minScalableThreads = 5;
    private static final int maxScalableThreads = 10;


    public static void main(String[] args) throws InterruptedException {
        try {
            ThreadPool fixedThreadPool = new FixedThreadPool(fixedThreadPoolThreads);
            ThreadPool scalableThreadPool = new ScalableThreadPool(minScalableThreads, maxScalableThreads);
            System.out.println("========================== fixed thread pool test ==========================");
            threadPoolTest(fixedThreadPool);
            System.out.println("========================== scalable thread pool test ==========================");
            threadPoolTest(scalableThreadPool);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void threadPoolTest(ThreadPool threadPool) throws InterruptedException {
        threadPool.start();
        for (int i = 0; i < numOfTasks; i++) {
            int taskNumber = i;
            Runnable task = () -> {
                System.out.println("Executing task " + taskNumber + " on thread " + Thread.currentThread().getName());
                try {
                    Thread.sleep(timePerTaskMills);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            };
            threadPool.execute(task);
        }
        // Thread.sleep(10000);
        threadPool.shutdown();
        threadPool.awaitTermination();
    }
}
