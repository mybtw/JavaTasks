import java.util.*;

public class CollectionUtils {

    // Добавление всех элементов из одного списка в другой
    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    // Создание нового списка
    public static <T> List<T> newArrayList() {
        return new ArrayList<T>();
    }

    // Нахождение индекса элемента в списке
    public static <T> int indexOf(List<? extends T> source, T o) {
        return source.indexOf(o);
    }

    // Ограничение списка до определенного размера
    public static <T> List<T> limit(List<? extends T> source, int size) {
        List<T> result = new ArrayList<>();
        for (int i = 0; i < size && i < source.size(); i++) {
            result.add(source.get(i));
        }
        return result;
    }

    // Добавление элемента в список
    public static <T> void add(List<? super T> source, T o) {
        source.add(o);
    }

    // Удаление всех элементов одного списка из другого
    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    // Проверка, содержит ли список все элементы другого списка
    public static <T> boolean containsAll(List<? extends T> c1, List<? extends T> c2) {
        return new HashSet<>(c1).containsAll(c2);
    }

    // Проверка, содержит ли список хотя бы один элемент из другого списка
    public static <T> boolean containsAny(List<? extends T> c1, List<? extends T> c2) {
        for (T item : c2) {
            if (c1.contains(item)) {
                return true;
            }
        }
        return false;
    }

    // Фильтрация списка по диапазону значений
    public static <T extends Comparable<? super T>> List<T> range(List<? extends T> list, T min, T max) {
        List<T> result = new ArrayList<>();
        for (T item : list) {
            if (item.compareTo(min) >= 0 && item.compareTo(max) <= 0) {
                result.add(item);
            }
        }
        return result;
    }

    // Фильтрация списка по диапазону значений с использованием компаратора
    public static <T> List<T> range(List<? extends T> list, T min, T max, Comparator<? super T> comparator) {
        List<T> result = new ArrayList<>();
        for (T item : list) {
            if (comparator.compare(item, min) >= 0 && comparator.compare(item, max) <= 0) {
                result.add(item);
            }
        }
        return result;
    }
}
