package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\astaf\\IdeaProjects\\JavaTasks\\Lab10\\textFiles\\test.txt";
        List<Integer> numbers = new ArrayList<>();

        try {
            numbers = readNumbersFromFile(filePath);
        } catch (IOException | NumberFormatException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            return;
        }

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (Integer number : numbers) {
            Runnable task = () -> {
                try {
                    BigInteger factorial = calculateFactorial(number);
                    System.out.println("Факториал числа " + number + " = " + factorial);
                } catch (IllegalArgumentException e) {
                    System.err.println("Ошибка: " + e.getMessage());
                }
            };
            executor.execute(task);
        }

        executor.shutdown();

    }

    private static List<Integer> readNumbersFromFile(String filePath) throws IOException {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.addAll(Arrays.stream(line.split(" "))
                        .map(Integer::parseInt)
                        .toList());
            }
        }
        return numbers;
    }

    private static BigInteger calculateFactorial(int number) {
        if (number > 50 || number < 1) {
            throw new IllegalArgumentException("Число должно быть от 1 до 50 включительно");
        }
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= number; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}