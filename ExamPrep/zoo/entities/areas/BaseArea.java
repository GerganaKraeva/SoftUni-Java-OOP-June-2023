package zoo.entities.areas;

import zoo.entities.animals.Animal;
import zoo.entities.foods.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static zoo.common.ExceptionMessages.*;

public abstract class BaseArea implements Area {

    private String name;
    private int capacity;
    private List<Food> foods;
    private List<Animal> animals;

    protected BaseArea(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        foods = new ArrayList<>();
        animals = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(AREA_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public List<Animal> getAnimals() {
        return animals;
    }

    @Override
    public List<Food> getFoods() {
        return foods;
    }

    @Override
    public int sumCalories() {

        return foods.stream().mapToInt(Food::getCalories).sum();
    }

    @Override
    public void addAnimal(Animal animal) {
        if (animals.size() == capacity) {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY);
        }
        animals.add(animal);
    }

    @Override
    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    @Override
    public void addFood(Food food) {
        foods.add(food);

    }

    @Override
    public void feed() {
        animals.forEach(Animal::eat);

    }

    @Override
    public String getInfo() {

        String result=animals.isEmpty()
                ?"none"
                :animals.stream()
                .map(Animal::getName)
                .collect(Collectors.joining(" "));

//        StringBuilder sb=new StringBuilder();
//        sb.append(String.format("%s (%s):",name,this.getClass().getSimpleName())).append(System.lineSeparator());
//        sb.append(String.format("Animals: %s",result)).append(System.lineSeparator());
//        sb.append(String.format("Foods: %d",foods.size())).append(System.lineSeparator());
//        sb.append(String.format("Calories: %d",sumCalories())).append(System.lineSeparator());

       return String.format("%s (%s):%n"+
                "Animals: %s%n"+
                "Foods: %d%n"+
                "Calories: %d",name,this.getClass().getSimpleName(),result,foods.size(),sumCalories());

//        return sb.toString().trim();
    }

}
