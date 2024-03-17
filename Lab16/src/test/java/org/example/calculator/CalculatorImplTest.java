package org.example.calculator;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorImplTest {
    private final CalculatorImpl calculatorImpl = new CalculatorImpl();

    @Test
    void testFibonacciForNegativeInput() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            calculatorImpl.fibonacci(-1);
        });
        assertTrue(thrown.getMessage().contains("greater than or equal to 0"));
    }

    @Test
    void testFibonacciForZero() {
        assertEquals(0, calculatorImpl.fibonacci(0).size(), "The list should be empty for n=0");
    }

    @Test
    void testFibonacciForOne() {
        assertEquals(List.of(0), calculatorImpl.fibonacci(1), "The list should contain only one element [1] for n=1");
    }

    @Test
    void testFibonacciForTwo() {
        assertEquals(List.of(0, 1), calculatorImpl.fibonacci(2), "The list should contain [1, 1] for n=2");
    }

    @Test
    void testFibonacciForTen() {
        assertEquals(List.of(0, 1, 1, 2, 3, 5, 8, 13, 21, 34), calculatorImpl.fibonacci(10), "The list should match the first ten Fibonacci numbers");
    }
}