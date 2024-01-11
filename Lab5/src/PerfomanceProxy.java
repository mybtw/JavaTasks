import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class PerfomanceProxy implements InvocationHandler {
    private final Object delegate;

    public PerfomanceProxy(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!method.isAnnotationPresent(Metric.class)) return invoke(method, args);
        long start = System.nanoTime();
        Object result = method.invoke(delegate, args);
        long end = System.nanoTime();
        System.out.println("Время работы метода: " + (end - start) + " (наносек)");
        return result;
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
