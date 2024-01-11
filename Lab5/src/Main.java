import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) throws IntrospectionException {
        // task1
        Calculator calculator = new CalculatorImpl();
        System.out.println(calculator.calc(15));

        var calcClass = calculator.getClass();
        // task2
        printMethods(calcClass);

        // task3
        printGetters(calcClass);
        listGetters(calcClass);

        // task4
        checkStringConstants(calcClass);

        // task5
        Calculator delegate = new CalculatorImpl();
        Calculator calc = (Calculator) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                delegate.getClass().getInterfaces(),
                new CachedInvocationHandler(delegate));
        run(calc);

        // task 6
        Calculator calcul = (Calculator) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                delegate.getClass().getInterfaces(),
                new PerfomanceProxy(delegate));
        run(calcul);

        // task7
        Source source = new Source();
        source.setName("Test Name");
        source.setAge(30);

        Target target = new Target();
        BeanUtils.assign(target, source);

        System.out.println("Test Name = " + target.getName());
        System.out.println(30 == target.getAge());
        System.out.println(target.getTax());
    }


    private static void run(Calculator calculator) {
        System.out.println(calculator.calc(5));
        System.out.println(calculator.calc(12));
        System.out.println(calculator.calc(12));
        System.out.println(calculator.calc(5));
        System.out.println(calculator.calc(12));
        System.out.println(calculator.calc(12));
        System.out.println(calculator.calc(12));
    }


    public static void printMethods(Class<?> clazz) {
        Class<?> superclass = clazz;
        while (superclass != null) {
            Method[] methods = superclass.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println(method.getName() + ", Класс: " + superclass.getSimpleName() + ", Приватыный: " + Modifier.isPrivate(method.getModifiers()));
            }
            superclass = superclass.getSuperclass();
        }
    }

    public static void checkStringConstants(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (Modifier.isPublic(field.getModifiers()) &&
                    Modifier.isStatic(field.getModifiers()) &&
                    Modifier.isFinal(field.getModifiers()) &&
                    String.class.equals(field.getType())) {
                try {
                    String value = (String) field.get(null);

                    if (!field.getName().equals(value)) {
                        System.out.println("Несоответствие: поле " + field.getName() + " имеет значение " + value);
                    }
                } catch (IllegalAccessException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void printGetters(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (isGetter(method)) {
                System.out.println(method.getName());
            }
        }
    }

    public static boolean isGetter(Method method) {
        if (method.getParameterTypes().length != 0) return false;
        if (void.class.equals(method.getReturnType())) return false;
        if (method.getName().startsWith("get") || method.getName().startsWith("is")) {
            return Modifier.isPublic(method.getModifiers());
        }
        return false;
    }


    public static void listGetters(Class<?> beanClass) {
        try {
            BeanInfo info = Introspector.getBeanInfo(beanClass, Object.class);
            PropertyDescriptor[] propertyDescriptors = info.getPropertyDescriptors();

            for (PropertyDescriptor pd : propertyDescriptors) {
                if (pd.getReadMethod() != null) {
                    System.out.println("Getter method: " + pd.getReadMethod());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}