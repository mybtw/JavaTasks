package org.example.calculator;

import org.example.cacheable.Cacheable;
import org.example.repository.H2DB;

import java.util.List;

public interface Calculator {
    @Cacheable(H2DB.class)
    public List<Integer> fibonacci(int n);
}
