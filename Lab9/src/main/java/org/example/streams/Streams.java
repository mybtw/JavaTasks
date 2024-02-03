package org.example.streams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class Streams<T> {
    List<T> collection;

    private Streams(List<? extends T> collection) {
        this.collection = new ArrayList<>(collection);
    }

    public static <T> Streams<T> of(List<? extends T> list) {
        return new Streams<>(list);
    }

    public Streams<T> filter(Predicate<? super T> predicate) {
        List<T> filteredList = new ArrayList<>();
        for (T element : collection) {
            if (predicate.test(element)) {
                filteredList.add(element);
            }
        }
        return new Streams<>(filteredList);
    }

    public <R> Streams<R> transform(Function<? super T, ? extends R> transformer) {
        List<R> transformedList = new ArrayList<>();
        for (T element : collection) {
            transformedList.add(transformer.apply(element));
        }
        return new Streams<>(transformedList);
    }

    public <K, V> Map<K, V> toMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends V> valueMapper) {
        Map<K, V> map = new HashMap<>();
        for (T element : collection) {
            map.put(keyMapper.apply(element), valueMapper.apply(element));
        }
        return map;
    }
}
