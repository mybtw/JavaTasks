package org.example.calculator;

import java.util.ArrayList;
import java.util.List;

public class CalculatorImpl implements Calculator {
    @Override
    public List<Integer> fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n should be greater than or equal to 0");
        }
        if (n > 47) {
            throw new IllegalArgumentException("n is too large, max value is 47");
        }
        List<Integer> res = new ArrayList<>(n);

        if (n > 0) {
            res.add(0);
        }
        if (n > 1) {
            res.add(1);
        }
        for (int i = 2; i < n; i++) {
            res.add(res.get(i - 1) + res.get(i - 2));
        }
        return res;
    }
}
