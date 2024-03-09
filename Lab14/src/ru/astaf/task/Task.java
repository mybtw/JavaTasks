package ru.astaf.task;

import ru.astaf.exceptions.TaskExecutionException;

import java.util.concurrent.Callable;

public class Task<T> {
    private final Callable<? extends T> callable;
    private volatile T result = null;
    private volatile boolean isComputed = false;
    private volatile Exception exception = null;
    private final Object lock = new Object();


    public Task(Callable<? extends T> callable) {
        this.callable = callable;
    }

    public T get() {
        if (!isComputed) {
            synchronized (lock) {
                if (!isComputed) {
                    try {
                        System.out.println("Начинаем выполнение задачи");
                        result = callable.call();
                    } catch (Exception e) {
                        this.exception = e;
                        throw new TaskExecutionException("Исключение во время выполнения задания", e);
                    } finally {
                        isComputed = true;
                    }
                }
            }
        }
        if (exception != null) {
            throw new TaskExecutionException("Исключение во время выполнения задания", exception);
        }
        return result;
    }
}
