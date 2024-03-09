import ru.astaf.context.Context;
import ru.astaf.exceptions.TaskExecutionException;
import ru.astaf.manager.ExecutionManager;
import ru.astaf.manager.ExecutionManagerImpl;
import ru.astaf.task.Task;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final Random random = new Random();

    public static void main(String[] args) {
        System.out.println("============================== Task class test ==============================");
        testTask();
        System.out.println("============================== executorManagerTest 1 ==============================");
        executorManagerTest();
        System.out.println("============================== executorManagerTest 2 ==============================");
        executorManagerTest2();
        System.out.println("============================== executorManagerTest 3 ==============================");
        executorManagerTest3();

    }

    public static void executorManagerTest3() {
        ExecutionManager manager = new ExecutionManagerImpl();
        Runnable callback = () -> System.out.println("callback");
        Runnable[] tasks = new Runnable[12];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = () -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            };
        }

        Context context = manager.execute(callback, tasks);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        context.interrupt();

        System.out.println("Завершенные задачи: " + context.getCompletedTaskCount());
        System.out.println("Неудачные задачи: " + context.getFailedTaskCount());
        System.out.println("Прерванные задачи: " + context.getInterruptedTaskCount());
        System.out.println("Все задачи завершены: " + context.isFinished());
    }

    public static void executorManagerTest() {
        ExecutionManager manager = new ExecutionManagerImpl();
        Runnable callback = () -> System.out.println("callback");
        Runnable[] tasks = new Runnable[20];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = () -> {
                try {
                    Thread.sleep((long) (3000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if ((random.nextInt(10) + 1) % 2 == 0) {
                    int res = 5 / 0;
                }
            };
        }

        Context context = manager.execute(callback, tasks);
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        context.interrupt();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Завершенные задачи: " + context.getCompletedTaskCount());
        System.out.println("Неудачные задачи: " + context.getFailedTaskCount());
        System.out.println("Прерванные задачи: " + context.getInterruptedTaskCount());
        System.out.println("Все задачи завершены: " + context.isFinished());
    }


    public static void executorManagerTest2() {
        ExecutionManager manager = new ExecutionManagerImpl();
        Runnable callback = () -> System.out.println("callback");
        Runnable[] tasks = new Runnable[20];
        for (int i = 0; i < tasks.length; i++) {
            int finalI = i;
            tasks[i] = () -> {
                try {
                    Thread.sleep((long) (Math.random() * 5500));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if ((random.nextInt(10) + 1) % 2 == 0) {
                    int res = 5 / 0;
                }
            };
        }

        Context context = manager.execute(callback, tasks);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        context.interrupt();

        System.out.println("Завершенные задачи: " + context.getCompletedTaskCount());
        System.out.println("Неудачные задачи: " + context.getFailedTaskCount());
        System.out.println("Прерванные задачи: " + context.getInterruptedTaskCount());
        System.out.println("Все задачи завершены: " + context.isFinished());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void testTask() {
        Task<Integer> task = new Task<>(() -> {
            TimeUnit.SECONDS.sleep(2);
            return 42;
        });

        Runnable taskRunner = () -> {
            try {
                System.out.println("Result: " + task.get());
            } catch (TaskExecutionException e) {
                System.err.println("Task failed: " + e.getMessage());
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; i++) {
            executorService.submit(taskRunner);
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("Tasks interrupted: " + e.getMessage());
        }
    }
}
