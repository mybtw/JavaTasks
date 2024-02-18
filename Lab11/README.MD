# Как проверять проект?
+ FixedThreadPool и ScalableThreadPool реализованы в пакете threadpool.
В main есть функция threadPoolTest, принимающая pool и назначающая ему задачи, в которых просто поток ждет оперделенное кол-во времени.
Можно менять константы и тестировать разные сценарии: 
  + numOfTasks - кол-во задач на выполение 
  + timePerTaskMills - сколько времени в миллисекундах должна выполняться каждая задача
  + fixedThreadPoolThread - кол-во потоков для FixedThreadPool
  + minScalableThreads - миинимальное кол-во потоков для ScalableThreadPool
  + maxScalableThreads - максимальное кол-во потоков для ScalableThreadPool
+ Смысл каждого метода указан в интерфейсе ThreadPool JavaDoc комментарием
# Логи
    private static final int numOfTasks = 50;
    private static final int timePerTaskMills = 10;
    private static final int fixedThreadPoolThreads = 5;
    private static final int minScalableThreads = 2;
    private static final int maxScalableThreads = 10;
========================== fixed thread pool test ==========================
Executing task 3 on thread Thread-3
Executing task 1 on thread Thread-4
Executing task 0 on thread Thread-1
Executing task 4 on thread Thread-0
Executing task 2 on thread Thread-2
Executing task 7 on thread Thread-2
Executing task 6 on thread Thread-0
Executing task 8 on thread Thread-3
Executing task 5 on thread Thread-1
Executing task 9 on thread Thread-4
Executing task 11 on thread Thread-2
Executing task 14 on thread Thread-1
Executing task 10 on thread Thread-0
Executing task 12 on thread Thread-4
Executing task 13 on thread Thread-3
Executing task 17 on thread Thread-3
Executing task 18 on thread Thread-0
Executing task 19 on thread Thread-4
Executing task 15 on thread Thread-1
Executing task 16 on thread Thread-2
Executing task 20 on thread Thread-0
Executing task 23 on thread Thread-3
Executing task 24 on thread Thread-1
Executing task 22 on thread Thread-2
Executing task 21 on thread Thread-4
Executing task 26 on thread Thread-0
Executing task 25 on thread Thread-1
Executing task 28 on thread Thread-3
Executing task 27 on thread Thread-4
Executing task 29 on thread Thread-2
Executing task 30 on thread Thread-1
Executing task 32 on thread Thread-0
Executing task 31 on thread Thread-2
Executing task 34 on thread Thread-3
Executing task 33 on thread Thread-4
Executing task 37 on thread Thread-0
Executing task 35 on thread Thread-3
Executing task 38 on thread Thread-2
Executing task 36 on thread Thread-4
Executing task 39 on thread Thread-1
Executing task 42 on thread Thread-4
Executing task 44 on thread Thread-0
Executing task 41 on thread Thread-1
Executing task 43 on thread Thread-2
Executing task 40 on thread Thread-3
Executing task 46 on thread Thread-1
Executing task 47 on thread Thread-0
Executing task 45 on thread Thread-3
Executing task 48 on thread Thread-2
Executing task 49 on thread Thread-4
========================== scalable thread pool test ==========================
Executing task 0 on thread Thread-6
Executing task 1 on thread Thread-7
Executing task 2 on thread Thread-9
Executing task 4 on thread Thread-12
Executing task 3 on thread Thread-5
Executing task 5 on thread Thread-11
Executing task 9 on thread Thread-14
Executing task 6 on thread Thread-13
Executing task 7 on thread Thread-10
Executing task 8 on thread Thread-8
Executing task 11 on thread Thread-8
Executing task 13 on thread Thread-7
Executing task 10 on thread Thread-10
Executing task 12 on thread Thread-13
Executing task 15 on thread Thread-5
Executing task 14 on thread Thread-6
Executing task 16 on thread Thread-14
Executing task 17 on thread Thread-11
Executing task 18 on thread Thread-12
Executing task 19 on thread Thread-9
Executing task 21 on thread Thread-5
Executing task 24 on thread Thread-13
Executing task 22 on thread Thread-12
Executing task 20 on thread Thread-11
Executing task 23 on thread Thread-14
Executing task 26 on thread Thread-10
Executing task 27 on thread Thread-6
Executing task 25 on thread Thread-7
Executing task 28 on thread Thread-9
Executing task 29 on thread Thread-8
Executing task 30 on thread Thread-13
Executing task 34 on thread Thread-10
Executing task 39 on thread Thread-14
Executing task 31 on thread Thread-12
Executing task 33 on thread Thread-6
Executing task 32 on thread Thread-7
Executing task 36 on thread Thread-8
Executing task 38 on thread Thread-11
Executing task 37 on thread Thread-5
Executing task 35 on thread Thread-9
Executing task 40 on thread Thread-9
Executing task 43 on thread Thread-10
Executing task 42 on thread Thread-6
Executing task 44 on thread Thread-14
Executing task 41 on thread Thread-8
Executing task 45 on thread Thread-12
Executing task 46 on thread Thread-11
Executing task 48 on thread Thread-13
Executing task 47 on thread Thread-5
Executing task 49 on thread Thread-7

    private static final int numOfTasks = 5;
    private static final int timePerTaskMills = 100;
    private static final int fixedThreadPoolThreads = 10;
    private static final int minScalableThreads = 10;
    private static final int maxScalableThreads = 15;
========================== fixed thread pool test ==========================
Executing task 3 on thread Thread-8
Executing task 2 on thread Thread-7
Executing task 1 on thread Thread-9
Executing task 0 on thread Thread-0
Executing task 4 on thread Thread-6
========================== scalable thread pool test ==========================
Executing task 0 on thread Thread-19
Executing task 1 on thread Thread-10
Executing task 2 on thread Thread-13
Executing task 4 on thread Thread-22
Executing task 3 on thread Thread-14
