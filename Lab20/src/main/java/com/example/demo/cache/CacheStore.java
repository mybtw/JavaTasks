package com.example.demo.cache;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

public class CacheStore {
    private final Map<String, CachedResponse> cache = Collections.synchronizedMap(new WeakHashMap<>());

    public void put(String url, CachedResponse response) {
        cache.put(url, response);
    }

    public CachedResponse get(String url) {
        return cache.get(url);
    }

    public record CachedResponse(String body, Map<String, String> headers, LocalDateTime cachedAt) {
    }
}
