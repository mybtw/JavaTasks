import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanUtils {
    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to   Object which properties will be set.
     * @param from Object which properties will be used to get values.
     */
    public static void assign(Object to, Object from) {
        Class<?> toClass = to.getClass();
        Class<?> fromClass = from.getClass();

        Map<String, Method> getters = findGetters(fromClass);
        Map<String, Method> setters = findSetters(toClass);

        for (Map.Entry<String, Method> getterEntry : getters.entrySet()) {
            String propName = getterEntry.getKey();
            Method getterMethod = getterEntry.getValue();

            if (setters.containsKey(propName)) {
                Method setterMethod = setters.get(propName);
                if (isCompatible(getterMethod, setterMethod)) {
                    try {
                        Object value = getterMethod.invoke(from);
                        setterMethod.invoke(to, value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    private static Map<String, Method> findGetters(Class<?> clazz) {
        Map<String, Method> getters = new HashMap<>();
        for (Method method : clazz.getMethods()) {
            if (isGetter(method)) {
                String propName = method.getName().startsWith("is")
                        ? method.getName().substring(2)
                        : method.getName().substring(3);
                getters.put(propName, method);
            }
        }
        return getters;
    }

    private static Map<String, Method> findSetters(Class<?> clazz) {
        Map<String, Method> setters = new HashMap<>();
        for (Method method : clazz.getMethods()) {
            if (isSetter(method)) {
                String propName = method.getName().substring(3);
                setters.put(propName, method);
            }
        }
        return setters;
    }

    private static boolean isGetter(Method method) {
        if (!method.getName().startsWith("get") && !method.getName().startsWith("is")) {
            return false;
        }
        if (method.getParameterTypes().length != 0) {
            return false;
        }
        return !void.class.equals(method.getReturnType());
    }

    private static boolean isSetter(Method method) {
        return method.getName().startsWith("set") &&
                method.getParameterTypes().length == 1 &&
                void.class.equals(method.getReturnType());
    }

    private static boolean isCompatible(Method getter, Method setter) {
        return setter.getParameterTypes()[0].isAssignableFrom(getter.getReturnType());
    }
}
