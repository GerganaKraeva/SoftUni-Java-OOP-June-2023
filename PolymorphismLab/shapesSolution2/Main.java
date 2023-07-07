package shapesSolution2;

public class Main {
    public static void main(String[] args) {
        Shape circle = new Circle(13.4);
        Shape rectangle = new Rectangle(3.0, 2.0);
        printShape(circle);
        printShape(rectangle);
    }

    private static void printShape(Shape shape) {
        System.out.println(shape.calculatePerimeter());
        System.out.println(shape.calculateArea());
    }
}
