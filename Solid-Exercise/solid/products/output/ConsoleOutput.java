package solid.products.output;

import solid.products.Product;
import solid.products.calculator.Calculator;

import java.util.List;

public class ConsoleOutput implements Output {
    private static final String SUM = "Sum: %f";
    private static final String AVERAGE = "Average: %f";


    public void outputSum(double sum) {
        System.out.printf((SUM) + "%n", sum);
    }

    public void outputAverage(double average) {
        System.out.printf((AVERAGE) + "%n", average);
    }
}