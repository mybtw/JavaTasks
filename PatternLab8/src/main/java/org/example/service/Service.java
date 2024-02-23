package org.example.service;

import org.example.proxy.cache.Cache;

import java.util.Date;
import java.util.List;

import static org.example.proxy.cache.CacheType.FILE;
import static org.example.proxy.cache.CacheType.IN_MEMORY;

public interface Service {
    @Cache(cacheType = FILE, cacheKey = "data", zip = true, identityBy = {String.class, Double.class}, listLimit = 10)
    List<String> run(String item, Double value, Date date) throws InterruptedException;

    @Cache(cacheType = IN_MEMORY, listLimit = 10, cacheKey = "workMethod", identityBy = {String.class})
    List<String> work(String item, Integer counter) throws InterruptedException;


}
