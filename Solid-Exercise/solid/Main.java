package solid;

import solid.products.calculator.CalorieCalculator;
import solid.products.output.ConsoleOutput;

public class Main {

    public static void main(String[] args) {
        CalorieCalculator calorieCalculator=new CalorieCalculator();
        ConsoleOutput consoleOutput =new ConsoleOutput(calorieCalculator);

    }
}
