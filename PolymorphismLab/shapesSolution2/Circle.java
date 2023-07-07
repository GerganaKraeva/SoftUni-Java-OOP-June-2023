package shapesSolution2;

public class Circle implements Shape {
    private Double radius;

    public Circle(Double radius) {
        this.radius = radius;
    }

    @Override
    public Double calculatePerimeter() {
        return Math.PI*2*this.radius;
    }

    @Override
    public Double calculateArea() {
        return Math.PI*Math.pow(this.radius,2);
    }
}
