package foodShortage;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, Buyer> people = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            String[] info = scanner.nextLine().split("\\s+");
            if (info.length == 4) {
                String name = info[0];
                int age = Integer.parseInt(info[1]);
                String id = info[2];
                String birthDate = info[3];
                Buyer buyer = new Citizen(name, age, id, birthDate);
                people.put(name, buyer);
            } else if (info.length == 3) {
                String name = info[0];
                int age = Integer.parseInt(info[1]);
                String group = info[2];
                Rebel rebel = new Rebel(name, age, group);
                people.put(name, rebel);
            }
        }
        String command = scanner.nextLine();
        int count = 0;
        while (!"End".equals(command)) {
            if (people.containsKey(command)) {
                people.get(command).buyFood();
                count += people.get(command).getFood();
            }
            command = scanner.nextLine();
        }
        System.out.println(count);
    }
}
