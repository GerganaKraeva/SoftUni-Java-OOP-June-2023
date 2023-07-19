import java.lang.reflect.*;
import java.util.Arrays;

public class _1_Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class<Reflection> clazz = Reflection.class;
        System.out.println(clazz);
        System.out.println(clazz.getSuperclass());
        Class[] interfaces = clazz.getInterfaces();
        Arrays.stream(interfaces).forEach(System.out::println);
        Constructor<Reflection> constructor = clazz.getDeclaredConstructor();
        Reflection reflection = constructor.newInstance();
        System.out.println(reflection);
    }
}
