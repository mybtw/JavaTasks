package org.example.proxy.cache;

import org.example.util.CacheValidator;
import org.example.util.SerializationUtil;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class CachedInvocationHandler implements InvocationHandler {

    private final Map<Object, Object> resultByArg = new HashMap<>();
    private final Object delegate;

    private Set<String> cachedFileNames;

    private final String rootFolder;

    public CachedInvocationHandler(Object delegate, String rootFolder) {
        this.delegate = delegate;
        this.rootFolder = rootFolder;
        this.cachedFileNames = new HashSet<>();
        loadFileNamesFromFolder(rootFolder);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Cache cache = method.getAnnotation(Cache.class);
        if (cache == null) return invoke(method, args);
        CacheValidator.validate(cache);
        return switch (cache.cacheType()) {
            case IN_MEMORY -> handleInMemoryCache(cache, method, args);
            case FILE -> handleFileCache(cache, method, args);
            default -> throw new IllegalStateException("Неподдерживаемый тип кеша: " + cache.cacheType());
        };
    }

    public void loadFileNamesFromFolder(String folderPath) {
        File folder = new File(folderPath);
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && (file.getName().endsWith(".zip") || file.getName().endsWith(".obj"))) {
                        cachedFileNames.add(file.getName());
                    }
                }
            }
        }
    }

    private Object handleFileCache(Cache cache, Method method, Object[] args) throws Throwable {
        var fileKey = getFileKey(cache, method);
        if (cachedFileNames.contains(fileKey)) {
            FileSaveObj obj = loadFromFile(fileKey);
            if (Arrays.equals(obj.getIdentityBy(), getRequiredFields(cache, method, args))) {
                return obj.getResult();
            }
        }
        System.out.println("Delegation in file of " + method.getName());
        Object result = invoke(method, args);
        if (cache.listLimit() != Integer.MAX_VALUE && result instanceof List) {
            result = limitListSize((List<?>) result, cache.listLimit());
        }
        writeToFile(new FileSaveObj<>(cache.identityBy().length == 0 ? args : getRequiredFields(cache, method, args), result), rootFolder + "\\" + fileKey);
        cachedFileNames.add(fileKey);
        return result;
    }

    public Object[] getRequiredFields(Cache cache, Method method, Object[] args) {
        List<Object> res = new ArrayList<>();
        res.add(method.getName());
        // Фильтрация args на основе типов, указанных в identityBy
        for (Object arg : args) {
            for (Class<?> type : cache.identityBy()) {
                if (type.isInstance(arg)) {
                    res.add(arg);
                }
            }
        }
        return res.toArray();
    }

    private FileSaveObj<Object> loadFromFile(String filename) throws IOException, ClassNotFoundException {
        return SerializationUtil.deserializeFileSaveObj(rootFolder + "\\" + filename);
    }


    private void writeToFile(FileSaveObj<Object> fileSaveObj, String filename) throws IOException {
        SerializationUtil.serializeFileSaveObj(filename, fileSaveObj);
    }

    private Object handleInMemoryCache(Cache cache, Method method, Object[] args) throws Throwable {
        var cacheKey = getKey(cache, method, args);
        if (!resultByArg.containsKey(cacheKey)) {
            System.out.println("Delegation in memory of " + method.getName());
            Object result = invoke(method, args);
            if (cache.listLimit() != Integer.MAX_VALUE && result instanceof List) {
                result = limitListSize((List<?>) result, cache.listLimit());
            }
            resultByArg.put(cacheKey, result);
            return result;
        }
        return resultByArg.get(cacheKey);
    }

    private String getFileKey(Cache cache, Method method) {
        String res = cache.cacheKey().isEmpty() ? method.getName() : cache.cacheKey();
        res += cache.zip() ? ".zip" : ".bin";
        return res;
    }

    private Object getKey(Cache cache, Method method, Object[] args) {
        List<Object> res = new ArrayList<>();
        res.add(method);

        if (cache.identityBy().length == 0) {
            res.addAll(Arrays.asList(args));
        } else {
            // Фильтрация args на основе типов, указанных в identityBy
            for (Object arg : args) {
                for (Class<?> type : cache.identityBy()) {
                    if (type.isInstance(arg)) {
                        res.add(arg);
                    }
                }
            }
        }

        if (cache.cacheType() == CacheType.IN_MEMORY && !cache.cacheKey().isEmpty()) {
            res.add(cache.cacheKey());
        }

        return res;
    }


    private List<?> limitListSize(List<?> list, int limit) {
        return list.size() <= limit ? list : new ArrayList<>(list.subList(0, limit));
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

}
