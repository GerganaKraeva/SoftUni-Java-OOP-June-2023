package zoo.core;

import zoo.entities.animals.Animal;
import zoo.entities.animals.AquaticAnimal;
import zoo.entities.animals.TerrestrialAnimal;
import zoo.entities.areas.Area;
import zoo.entities.areas.LandArea;
import zoo.entities.areas.WaterArea;
import zoo.entities.foods.Food;
import zoo.entities.foods.Meat;
import zoo.entities.foods.Vegetable;
import zoo.repositories.FoodRepository;
import zoo.repositories.FoodRepositoryImpl;


import java.util.*;
import java.util.stream.Collectors;

import static zoo.common.ConstantMessages.*;
import static zoo.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private FoodRepository foodRepository;
    private Map<String, Area> areas ;

    public ControllerImpl() {
        this.foodRepository = new FoodRepositoryImpl();
        this.areas = new LinkedHashMap<>();
    }

    @Override
    public String addArea(String areaType, String areaName) {
        Area area;
        if ("LandArea".equals(areaType)) {
            area = new LandArea(areaName);
        } else if ("WaterArea".equals(areaType)) {
            area = new WaterArea(areaName);
        } else {
            throw new IllegalArgumentException(INVALID_AREA_TYPE);
        }
        areas.put(areaName, area);
        return String.format(SUCCESSFULLY_ADDED_AREA_TYPE, areaType);
    }

    @Override
    public String buyFood(String foodType) {
        Food food;
        if ("Meat".equals(foodType)) {
            food = new Meat();
        } else if ("Vegetable".equals(foodType)) {
            food = new Vegetable();
        } else {
            throw new NullPointerException(INVALID_FOOD_TYPE);
        }
        foodRepository.add(food);
        return String.format(SUCCESSFULLY_ADDED_FOOD_TYPE, foodType);
    }

    @Override
    public String foodForArea(String areaName, String foodType) {
        Food foodForArea = foodRepository.findByType(foodType);
        if (foodForArea == null) {
            throw new IllegalArgumentException(String.format(NO_FOOD_FOUND, foodType));
        }
        areas.get(areaName).addFood(foodForArea);
        foodRepository.remove(foodForArea);

        return (String.format(SUCCESSFULLY_ADDED_FOOD_IN_AREA, foodType, areaName));
    }


    @Override
    public String addAnimal(String areaName, String animalType, String animalName, String kind, double price) {
        Animal animal;

        if ("AquaticAnimal".equals(animalType)) {
            animal = new AquaticAnimal(animalName, kind, price);
        } else if ("TerrestrialAnimal".equals(animalType)) {
            animal = new TerrestrialAnimal(animalName, kind, price);
        } else {
            throw new IllegalArgumentException(INVALID_ANIMAL_TYPE);
        }

        Area area=areas.get(areaName);
        String areaType=area.getClass().getSimpleName();

        Boolean areaAndAnimalAreLandBased="LandArea".equals(areaType) && "TerrestrialAnimal".equals(animalType);
        Boolean areaAndAnimalAreWaterBased="WaterArea".equals(areaType) && "AquaticAnimal".equals(animalType);

        if(areaAndAnimalAreLandBased || areaAndAnimalAreWaterBased){
            area.addAnimal(animal);
        }else{
            return AREA_NOT_SUITABLE;
        }
        return String.format(SUCCESSFULLY_ADDED_AREA_TYPE,animalType);

    }

    @Override
    public String feedAnimal(String areaName) {
        Area area = areas.get(areaName);
        area.feed();

        return String.format(ANIMALS_FED, area.getAnimals().size());
    }

    @Override
    public String calculateKg(String areaName) {
        Collection <Animal> animals = areas.get(areaName).getAnimals();
        double calculateKg= animals.stream().mapToDouble(Animal::getKg).sum();
        return String.format(KILOGRAMS_AREA,areaName,calculateKg);
    }

    @Override
    public String getStatistics() {
      return   areas.values().stream()
                .map(Area::getInfo)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
