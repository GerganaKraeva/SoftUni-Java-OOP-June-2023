import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class _2_Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Reflection> clazz = Reflection.class;

        Constructor<Reflection> constructor = clazz.getDeclaredConstructor();

        Method[] methods = clazz.getDeclaredMethods();

        Arrays.stream(methods).sorted(Comparator.comparing(Method::getName)).forEach(m -> {
            if (m.getName().startsWith("get")) {
                System.out.println(String.format("%s will return class %s", m.getName(), m.getReturnType().getTypeName()));
            } else if (m.getName().startsWith("set") &&
                    m.getParameterTypes().length == 1) {
                System.out.println(String.format("%s and will set field of class %s", m.getName(),
                        m.getParameterTypes()[0].getTypeName()));
            }
        });

    }
}
