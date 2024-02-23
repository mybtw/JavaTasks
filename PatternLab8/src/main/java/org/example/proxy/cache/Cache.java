package org.example.proxy.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cache {
    /**
     * Определяет тип кеша.
     * CacheType.IN_MEMORY для хранения в памяти JVM,
     * CacheType.FILE для хранения в файловой системе.
     * По умолчанию используется кеш в памяти.
     */
    CacheType cacheType() default CacheType.IN_MEMORY;
    /**
     * Задаёт имя файла или ключ кеша.
     * Если не указано, используется имя метода.
     */
    String cacheKey() default "";
    /**
     * Указывает, следует ли сжимать файл кеша.
     * Используется только при cacheType = CacheType.FILE.
     * По умолчанию сжатие не используется.
     */
    boolean zip() default false;

    /**
     * Указывает классы параметров метода, которые учитываются при определении уникальности вызова.
     * Если пусто, учитываются все параметры метода.
     */
    Class<?>[] identityBy() default {};

    /**
     * Определяет максимальное количество элементов, сохраняемых в кеше, если возвращаемый тип - List.
     * По умолчанию ограничение на количество элементов отсутствует.
     */
    int listLimit() default Integer.MAX_VALUE;
}
