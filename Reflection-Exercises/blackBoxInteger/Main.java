package blackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class<BlackBoxInt>blackBoxIntClass= BlackBoxInt.class;
        Constructor<BlackBoxInt>blackBoxIntConstructor=blackBoxIntClass.getDeclaredConstructor();
        blackBoxIntConstructor.setAccessible(true);
        BlackBoxInt blackBoxInt =blackBoxIntConstructor.newInstance();
        Scanner scanner=new Scanner(System.in);
        String command=scanner.nextLine();

        while (!command.equals("END")){
            String methodName=command.split("_")[0];
            int number= Integer.parseInt(command.split("_")[1]);
            Method method=blackBoxIntClass.getDeclaredMethod(methodName, int.class);
            method.setAccessible(true);
            method.invoke(blackBoxInt, number);

            Field innerValueField = blackBoxIntClass.getDeclaredField("innerValue"); //взимаме числото в кутията
            innerValueField.setAccessible(true);
            System.out.println(innerValueField.get(blackBoxInt));

            command=scanner.nextLine();
        }

    }
}
