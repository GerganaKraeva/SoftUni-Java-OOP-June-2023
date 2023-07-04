package borderControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        List<Identifiable> identifiables = new ArrayList<>();
        while (!line.equals("End")) {
            Identifiable identifiable;
            String[] info = line.split("\\s+");
            if (info.length == 3) {
                String name = info[0];
                int age = Integer.parseInt(info[1]);
                String id = info[2];
                identifiable = new Citizen(name, age, id);
            } else {
                identifiable = new Robot(info[0], info[1]);
            }
            identifiables.add(identifiable);
            line = scanner.nextLine();
        }
        String fakeIdPostfix=scanner.nextLine();

        identifiables.stream()
                .map(Identifiable::getId)
                .filter(id->id.endsWith(fakeIdPostfix))
                .forEach(System.out::println);
    }
}
