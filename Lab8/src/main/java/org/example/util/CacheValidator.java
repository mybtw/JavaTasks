package org.example.util;

import org.example.proxy.cache.Cache;

public class CacheValidator {
    public static void validate(Cache cache) {
        if (cache.listLimit() < 0) {
            throw new IllegalArgumentException("List size limit should be greater than 0");
        }
    }
}
