package shoppingSpree;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Person> people = new LinkedHashMap<>();
        Map<String, Product> products = new LinkedHashMap<>();


        Arrays.stream(scanner.nextLine().split(";"))
                .forEach(p -> {
                    String[] tokens = p.split("=");
                    Person person = new Person(tokens[0], Double.parseDouble(tokens[1]));
                    people.putIfAbsent(person.getName(), person);
                });

        String[] productsTaken = scanner.nextLine().split(";");
        Arrays.stream(scanner.nextLine().split(";"))
                .forEach(p -> {
                    String[] tokens = p.split("=");
                    Product product = new Product(tokens[0], Double.parseDouble(tokens[1]));
                    products.putIfAbsent(product.getName(), product);
                });
        String command = scanner.nextLine();
        while (!command.equals("END")) {
            String[] tokens = command.split("\\s+");
            String personName = tokens[0];
            String productName = tokens[1];
            Person person = people.get(personName);
            Product product = products.get(productName);
            person.buyProduct(product);
            command = scanner.nextLine();
        }
        people.values().forEach(p -> {
            if (p.getProducts().size() == 0) {
                System.out.printf("%s - Nothing bought.\n", p.getName());
            } else {
                System.out.printf("%s - ", p.getName());
                String productsName = p.getProducts()
                        .stream()
                        .map(Product::getName)
                        .collect(Collectors.joining(", "));
                System.out.print(productsName);
            }
        });
    }
}