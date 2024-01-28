package org.example.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceImpl implements Service {
    @Override
    public List<String> run(String item, Double value, Date date) throws InterruptedException {
        Thread.sleep(5000);
        List<String> strings = new ArrayList<>(1000);
        for (int i = 0; i < 1000; i++) {
            strings.add("run " + (i + 1));
        }
        return strings;

    }

    @Override
    public List<String> work(String item, Integer counter) throws InterruptedException {
        Thread.sleep(2000);
        List<String> strings = new ArrayList<>(1000);
        for (int i = 0; i < 1000; i++) {
            strings.add("work " + (i + 1));
        }
        return strings;
    }
}
