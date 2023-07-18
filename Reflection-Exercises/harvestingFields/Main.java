package harvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Class<RichSoilLand> clazz = RichSoilLand.class;
        Field[] fields = clazz.getDeclaredFields();
        Consumer<Field> fieldPrinter = field -> System.out.printf("%s %s %s\n", Modifier.toString(field.getModifiers()), field.getType().getSimpleName(), field.getName());

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equals("HARVEST")) {
            if (command.equals("all")) {
                Arrays.stream(fields).forEach(fieldPrinter);
            } else {
                String finalCommand = command;
                Arrays.stream(fields)
                        .filter(field -> Modifier.toString(field.getModifiers()).equals(finalCommand))
                        .forEach(fieldPrinter);
            }
            command = scanner.nextLine();
        }
    }
}
