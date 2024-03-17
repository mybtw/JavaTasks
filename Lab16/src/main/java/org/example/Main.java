package org.example;

import org.example.cacheable.CacheableInvocationHandler;
import org.example.calculator.Calculator;
import org.example.calculator.CalculatorImpl;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        CalculatorImpl delegate = new CalculatorImpl();
        Calculator calc = (Calculator) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                delegate.getClass().getInterfaces(),
                new CacheableInvocationHandler(delegate));
        run(calc);
        // runTest2(calc);
    }

    private static void run(Calculator calculator) {
        System.out.println(calculator.fibonacci(5));
        System.out.println(calculator.fibonacci(12));
        System.out.println(calculator.fibonacci(4));
        System.out.println(calculator.fibonacci(5));
        System.out.println(calculator.fibonacci(12));
        System.out.println(calculator.fibonacci(4));
        System.out.println(calculator.fibonacci(12));
    }


//    private static void runTest2(Calculator calculator) {
//
//        for (int i = 1; i <= 47; i++) {
//
//            for (int j = 0; j < 3; j++) {
//                System.out.println("fibonacci(" + i + ") = " + calculator.fibonacci(i));
//            }
//        }
//    }

}