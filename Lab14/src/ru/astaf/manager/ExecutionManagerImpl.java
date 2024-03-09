package ru.astaf.manager;

import ru.astaf.context.Context;
import ru.astaf.task.TrackableRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutionManagerImpl implements ExecutionManager {
    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        ExecutionManagerContext context = new ExecutionManagerContext(tasks.length);
        ExecutorService callbackExecutor = Executors.newSingleThreadExecutor();

        List<TrackableRunnable> trackableTasks = new ArrayList<>();

        for (Runnable task : tasks) {
            TrackableRunnable trackableTask = new TrackableRunnable(task);
            trackableTasks.add(trackableTask);
            Future<?> future = executor.submit(() -> {
                if (!context.isInterrupted()) {
                    try {
                        trackableTask.run();
                        System.out.println("Задача завершена");
                        context.taskCompleted();
                    } catch (Exception e) {
                        context.taskFailed();
                        System.out.println("Задача выбросила исключение");
                    }
                }
            });
            trackableTask.setFuture(future);
        }

        context.setTrackableTasks(trackableTasks);

        executor.shutdown();

        callbackExecutor.submit(() -> {
            try {
                executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
                callback.run();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                callbackExecutor.shutdown();
            }
        });
        return context;
    }

    private static class ExecutionManagerContext implements Context {
        private final AtomicInteger completedTaskCount = new AtomicInteger();
        private final AtomicInteger failedTaskCount = new AtomicInteger();
        private final AtomicInteger interruptedTaskCount = new AtomicInteger();
        private volatile boolean isInterrupted = false;
        private final int totalTasks;
        private List<TrackableRunnable> trackableTasks;



        public ExecutionManagerContext(int totalTasks) {
            this.totalTasks = totalTasks;
        }

        public void setTrackableTasks(List<TrackableRunnable> trackableTasks) {
            this.trackableTasks = trackableTasks;
        }

        @Override
        public int getCompletedTaskCount() {
            return completedTaskCount.get();
        }

        @Override
        public int getFailedTaskCount() {
            return failedTaskCount.get();
        }

        @Override
        public int getInterruptedTaskCount() {
            return interruptedTaskCount.get();
        }

        @Override
        public synchronized void interrupt() {
            isInterrupted = true;
            int cnt = 0;
            for (TrackableRunnable task : trackableTasks) {
                if  (task.cancelIfNotStarted()) {
                    cnt++;
                }
            }
            interruptedTaskCount.getAndAdd(cnt);
        }

        @Override
        public synchronized boolean isFinished() {
            return getCompletedTaskCount() + getInterruptedTaskCount() == totalTasks;
        }

        public boolean isInterrupted() {
            return isInterrupted;
        }

        public void taskCompleted() {
            completedTaskCount.incrementAndGet();
        }

        public void taskFailed() {
            failedTaskCount.incrementAndGet();
        }
    }
}
