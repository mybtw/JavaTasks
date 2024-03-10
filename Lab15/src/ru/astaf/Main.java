package ru.astaf;

import java.util.HashMap;
import java.util.Map;

public class Main {

    private static Map<Integer, String> map = new HashMap<>();

    public static void main(String[] args) {
        testJIT();
        System.out.println(System.getProperty("java.version"));
        testGC();
    }

    public static void testJIT() {
        for (int i = 0; i < 100_000; i++) {
            map.put(i, "value " + i);
        }
    }

    public static void testGC() {
        while (true) {
            int arraySize = 1000;
            long[][] arrayOfLongs = new long[arraySize][arraySize];
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }
    }
}
