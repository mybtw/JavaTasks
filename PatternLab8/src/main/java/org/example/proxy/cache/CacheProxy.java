package org.example.proxy.cache;

import java.lang.reflect.Proxy;

public class CacheProxy {
        private String cacheDir;
        //private Map<Method, MethodCache> methodCaches = new ConcurrentHashMap<>();

        public CacheProxy(String cacheDir) {
            this.cacheDir = cacheDir;
        }

        public <T> T cache(T delegate) {
            return (T) Proxy.newProxyInstance(
                    delegate.getClass().getClassLoader(),
                    delegate.getClass().getInterfaces(),
                    new CachedInvocationHandler(delegate, cacheDir)
            );
        }
}
