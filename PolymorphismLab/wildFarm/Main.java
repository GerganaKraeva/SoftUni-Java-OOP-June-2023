package wildFarm;

import wildFarm.animal.*;
import wildFarm.food.Food;
import wildFarm.food.FoodType;
import wildFarm.food.Meat;
import wildFarm.food.Vegetable;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        while (!"End".equals(line)) {
            String[] info = line.split("\\s+");
            String[] foodParts = scanner.nextLine().split("\\s+");

            Animal animal = createAnimal(info);
            Food food = createFood(foodParts);

            animal.makeSound();
            animal.eat(food);
            System.out.println(animal);

            line = scanner.nextLine();
        }
    }

    private static Food createFood(String[] foodParts) {
        FoodType foodType = FoodType.valueOf(foodParts[0]);
        int quantity=Integer.parseInt(foodParts[1]);
        switch (foodType) {
            case Vegetable:
                return new Vegetable(quantity);
            case Meat:
                return new Meat(quantity);
            default:
                return null;
        }
    }

    private static Animal createAnimal(String[] info) {
        AnimalType type = AnimalType.valueOf(info[0]);
        switch (type) {
            case Cat:
                return new Cat(info[1], Double.parseDouble(info[2]), info[3], info[4]);
            case Tiger:
                return new Tiger(info[1], Double.parseDouble(info[2]), info[3]);
            case Zebra:
                return new Zebra(info[1], Double.parseDouble(info[2]), info[3]);
            case Mouse:
                return new Mouse(info[1], Double.parseDouble(info[2]), info[3]);
            default:
                return null;
        }
    }
}
