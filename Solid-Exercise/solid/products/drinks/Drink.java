package solid.products.drinks;

import solid.products.Product;

public abstract class Drink implements Product {
    private double milliliters;
    private double caloriesPer100Grams;
    private double destiny;

    public Drink(double milliliters, double caloriesPer100Grams, double destiny) {
        this.milliliters = milliliters;
        this.caloriesPer100Grams = caloriesPer100Grams;
        this.destiny = destiny;
    }

    public double getMilliliters() {
        return milliliters;
    }

    public double getCalories() {
        double grams = getMilliliters() * destiny;
        return (caloriesPer100Grams / 100) * grams;
    }

    public double getLiters() {
        return milliliters * 1000;
    }

    @Override
    public double getKilograms() {
        return getLiters()*destiny;
    }
}
