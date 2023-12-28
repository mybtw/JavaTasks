import java.util.Map;

public interface CountMap<T> {
    void add(T o);
    int getCount(T o);
    int remove(T o);
    int size();
    void addAll(CountMap<? extends T> source);
    Map<T, Integer> toMap();
    void toMap(Map<? super T, Integer> destination);
}

