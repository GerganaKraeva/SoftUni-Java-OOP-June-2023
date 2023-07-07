package wildFarm.animal;

import wildFarm.food.Food;
import wildFarm.food.Meat;
import wildFarm.food.Vegetable;

public class Tiger extends Feline {


public Tiger(String name, double weight,String region) {
    super(name,weight,region,AnimalType.Tiger);
}
    @Override
    public void makeSound() {
        System.out.println("ROAAR!!!");
    }

    @Override
    public boolean willEatFood(Food food) {
        return food instanceof Meat;

    }


}
