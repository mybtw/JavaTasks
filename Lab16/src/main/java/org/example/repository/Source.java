package org.example.repository;

import java.sql.Connection;

public interface Source {
    /**
     * создает соединение с бд
     */
    public void initConnection();

    /**
     * закрывает соединение с бд
     */
    public void closeConnection();

    /**
     * создает таблицу для сохранения данных, если такой еще нет
     */
    public void createTable();

    /**
     * @return объект соединения с бд
     */
    public Connection getConnection();

    /**
     * Сохраняет объект в кеше или обновляет его, если ключ уже существует.
     *
     * @param key    - хэш код по методу и значениям его параметров
     * @param params - параметры метода
     * @param result - результат работы метода
     */
    public void saveOrUpdate(Object key, Object[] params, Object result);

    /**
     * Возвращает объект по ключу
     *
     * @param key
     */
    public Object get(Object key);

    /**
     * Проверяет, существует ли объект в кеше по заданному ключу
     *
     * @param key
     * @return
     */
    public boolean exists(Object key);

    /**
     * Удаляет объект из кеша по ключу.
     *
     * @param key
     */
    public void delete(Object key);

    /**
     * Очищает весь кеш.
     */
    public void clear();

}
