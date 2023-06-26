package pointInRectangle;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] input = getCoordinates(scanner.nextLine());
        Point bottomLeft = new Point(input[0], input[1]);
        Point topRight = new Point(input[2], input[3]);
        Rectangle rectangle = new Rectangle(bottomLeft, topRight);
        int inputCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < inputCount; i++) {
            int[] pointCoordinate = getCoordinates(scanner.nextLine());
            Point point = new Point(pointCoordinate[0], pointCoordinate[1]);
            boolean contains = rectangle.contains(point);
            System.out.println(contains);
        }
    }

    private static int [] getCoordinates(String input) {
   return  Arrays.stream(input.split("\\s+"))
           .mapToInt(Integer::parseInt)
           .toArray();
    }
}
