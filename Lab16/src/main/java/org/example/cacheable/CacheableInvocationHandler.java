package org.example.cacheable;

import org.example.repository.Source;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CacheableInvocationHandler implements InvocationHandler {

    private Source source;
    private final Object delegate;

    public CacheableInvocationHandler(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!method.isAnnotationPresent(Cacheable.class)) {
            return invoke(method, args);
        }
        try {
            Cacheable cacheable = method.getAnnotation(Cacheable.class);
            if (source == null || !source.getClass().equals(cacheable.value())) {
                source = cacheable.value().getDeclaredConstructor().newInstance();
            }
            if (source.getConnection().isClosed()) {
                source.initConnection();
            }
            Object key = key(method, args);
            if (source.exists(key.hashCode())) {
                System.out.println("Retrieving from cache: " + method.getName());
                return source.get(key.hashCode());
            } else {
                System.out.println("Delegation and caching: " + method.getName());
                Object result = invoke(method, args);
                source.saveOrUpdate(key.hashCode(), args, result);
                return result;
            }
        } finally {
            if (source != null) {
                source.closeConnection();
            }
        }
    }

    private Object invoke(Method method, Object[] args) throws Throwable {
        try {
            return method.invoke(delegate, args);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Impossible", e);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    private Object key(Method method, Object[] args) {
        List<Object> key = new ArrayList<>();
        key.add(method);
        key.addAll(Arrays.asList(args));
        return key;
    }
}
