package animalFarm;

public class Chicken {
    private String name;
    private int age;

    public Chicken(String name, int age) {
        this.setName(name);
        this.setAge(age);
    }

    private void setName(String name) {
        if (name.length() < 1) {
            throw new IllegalArgumentException("Name cannot be empty.");
        } else {
            this.name = name;
        }
    }

    private void setAge(int age) {
        if (age > 15 || age < 0) {
            throw new IllegalArgumentException("Age should be between 0 and 15.");
        } else {
            this.age = age;
        }
    }

    public double productPerDay() {
        return calculateProductPerDay();
    }

    private double calculateProductPerDay() {
        double productPerDay = 0;
        if (this.age < 6) {
            productPerDay = 2;
        } else if (this.age < 12) {
            productPerDay = 1;
        } else {
            productPerDay = 0.75;
        }
        return productPerDay;
    }


    @Override
    public String toString() {
        return String.format("Chicken %s (age %d) can produce 1.00 eggs per day.\n",
                this.name,
                this.age);
    }
}
