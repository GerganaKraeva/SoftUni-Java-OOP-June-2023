import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class _3_Main {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Scanner scanner = new Scanner(System.in);
        Class<Reflection> clazz = Reflection.class;
        Method[] methods = clazz.getDeclaredMethods();


        Arrays.stream(clazz.getDeclaredFields()).sorted(Comparator.comparing(Field::getName))
                .forEach(f -> {
                    if (!Modifier.isPrivate(f.getModifiers())) {
                        System.out.printf("%s must be private!\n", f.getName());
                    }
                });
        Arrays.stream(clazz.getDeclaredMethods()).sorted(Comparator.comparing(Method::getName))
                .forEach(m -> {
                    if (m.getName().startsWith("get") && !Modifier.isPublic(m.getModifiers())) {
                        System.out.printf("%s have to be public!\n", m.getName());
                    }
                });
        Arrays.stream(clazz.getDeclaredMethods()).sorted(Comparator.comparing(Method::getName))
                .forEach(m -> {
                    if (m.getName().startsWith("set") && !Modifier.isPrivate(m.getModifiers())) {

                        System.out.printf("%s have to be private!\n", m.getName());
                    }
                });

    }
}
