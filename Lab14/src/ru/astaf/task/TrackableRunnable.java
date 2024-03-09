package ru.astaf.task;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

public class TrackableRunnable implements Runnable {
    private final Runnable task;

    private final AtomicBoolean isStarted = new AtomicBoolean(false);
    private Future<?> future; // Для управления задачей после её постановки в очередь на выполнение

    public TrackableRunnable(Runnable task) {
        this.task = task;
    }

    @Override
    public void run() {
        if (!isStarted.getAndSet(true)) { // Устанавливаем флаг, что задача начала выполняться
            task.run();
        }
    }

    public void setFuture(Future<?> future) {
        this.future = future;
    }

    public boolean isStarted() {
        return isStarted.get();
    }


    public boolean cancelIfNotStarted() {
        if (!isStarted.get()) { // Если задача не начала выполняться, пытаемся её отменить
            future.cancel(true);
            return true;
        }
        return false;
    }
}
