package org.example.repository;

import org.example.config.DataConnection;

import java.sql.*;
import java.util.List;

public class H2DB implements Source {
    private Connection connection;

    public H2DB() {
        initConnection();
        createTable();
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void initConnection() {
        try {
            String url = DataConnection.url;
            connection = DriverManager.getConnection(url, DataConnection.user, DataConnection.password);
            System.out.println("Соединение с базой данных H2 установлено.");
        } catch (SQLException e) {
            System.out.println("Ошибка при установлении соединения с базой данных H2: " + e.getMessage());
        }
    }

    @Override
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Соединение с базой данных H2 закрыто.");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при закрытии соединения с базой данных H2: " + e.getMessage());
        }
    }

    @Override
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS fibonacci_sequences (" +
                "hash_code INT PRIMARY KEY," +
                "method_key INT," +
                "result INT ARRAY" +
                ");";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Таблица 'fibonacci_sequences' создана или уже существует.");
        } catch (SQLException e) {
            System.out.println("Ошибка при создании таблицы: " + e.getMessage());
        }
    }

    @Override
    public void saveOrUpdate(Object key, Object[] params, Object result) {
        if (!(key instanceof Integer)) {
            throw new IllegalArgumentException("Неверный тип ключа.");
        }
        if (params.length != 1 || !(params[0] instanceof Integer) || !(result instanceof List)) {
            throw new IllegalArgumentException("Ожидаются значения типа Integer и List<Integer>.");
        }
        int hashCode = (Integer) key;
        int n = (Integer) params[0];
        List<Integer> resultList = (List<Integer>) result;

        // SQL запрос для обновления или вставки данных
        String sql = "MERGE INTO fibonacci_sequences (hash_code, method_key, result) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, hashCode);
            pstmt.setInt(2, n);
            // Преобразование списка результатов в массив для сохранения в БД
            Integer[] resultArray = resultList.toArray(new Integer[0]);
            Array array = connection.createArrayOf("INTEGER", resultArray);
            pstmt.setArray(3, array);
            pstmt.executeUpdate();
            System.out.println("Данные сохранены/обновлены.");
        } catch (SQLException e) {
            System.out.println("Ошибка при сохранении/обновлении данных: " + e.getMessage());
        }
    }

    @Override
    public Object get(Object key) {
        if (!(key instanceof Integer)) {
            throw new IllegalArgumentException("Неверный тип ключа.");
        }
        String sql = "SELECT result FROM fibonacci_sequences WHERE hash_code = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, (Integer) key);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Array array = rs.getArray("result");
                Object[] objArray = (Object[]) array.getArray();
                Integer[] sequenceArray = new Integer[objArray.length];
                for (int i = 0; i < objArray.length; i++) {
                    sequenceArray[i] = (Integer) objArray[i];
                }
                return List.of(sequenceArray);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении данных: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean exists(Object key) {
        if (!(key instanceof Integer)) {
            throw new IllegalArgumentException("Неверный тип ключа.");
        }
        String sql = "SELECT 1 FROM fibonacci_sequences WHERE hash_code = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, (Integer) key);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Ошибка при проверке существования данных: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void delete(Object key) {
        if (!(key instanceof Integer)) {
            throw new IllegalArgumentException("Неверный тип ключа.");
        }
        String sql = "DELETE FROM fibonacci_sequences WHERE hash_code = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, (Integer) key);
            pstmt.executeUpdate();
            System.out.println("Данные удалены.");
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении данных: " + e.getMessage());
        }
    }

    @Override
    public void clear() {
        String sql = "DELETE FROM fibonacci_sequences";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Все данные удалены.");
        } catch (SQLException e) {
            System.out.println("Ошибка при очистке таблицы: " + e.getMessage());
        }
    }
}
