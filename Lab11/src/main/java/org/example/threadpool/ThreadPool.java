package org.example.threadpool;

public interface ThreadPool {
    /**
     * Запускает потоки. Потоки бездействуют, до тех пор пока не появится новое задание в очереди (см. execute)
     */
    void start();

    /**
     * @param runnable задание;
     *                 складывает это задание в очередь. Освободившийся поток должен выполнить это задание. Каждое задание должны быть выполнено ровно 1 раз
     */
    void execute(Runnable runnable);

    /**
     * инициирует процедуру плавной остановки пула потоков. После вызова этого метода пул перестает принимать новые задачи, но уже добавленные задачи будут выполнены до конца
     */
    void shutdown();

    /**
     * блокирует вызывающий поток до тех пор, пока не завершится процесс остановки пула потоков, инициированный методом shutdown
     *
     * @throws InterruptedException
     */
    void awaitTermination() throws InterruptedException;
}
