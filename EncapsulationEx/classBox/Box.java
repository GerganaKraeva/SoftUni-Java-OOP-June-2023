package classBox;

public class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        this.setLength(length);
        this.setWidth(width);
        this.setHeight(height);
    }

    private void setLength(double length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length cannot be zero or negative.");
        } else {
            this.length = length;
        }
    }


    private void setWidth(double width) {
        if (width <= 0) {
            throw new IllegalArgumentException("Width cannot be zero or negative.");
        } else {
            this.width = width;
        }
    }

    private void setHeight(double height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Height cannot be zero or negative.");
        } else {
            this.height = height;
        }
    }

    public double calculateSurfaceArea() {
        double surfaceArea = 0;
        surfaceArea = 2 * (this.height * this.length + this.height * this.width + this.width * this.length);
        return surfaceArea;
    }

    public double calculateLateralSurfaceArea() {
        double literalSurfaceArea = 0;
        literalSurfaceArea = 2 * (this.height * this.length + this.height * this.width);
        return literalSurfaceArea;
    }

    public double calculateVolume() {
        double volume = 0;
        volume = this.height * this.length * this.width;
        return volume;
    }

    @Override
    public String toString() {
        return String.format("Surface Area - %.2f\n" +
                        "Lateral Surface Area - %.2f\n" +
                        "Volume – %.2f\n",
                calculateSurfaceArea(),
                calculateLateralSurfaceArea(),
                calculateVolume());
    }
}
