package vehiclesExtension;

import java.text.DecimalFormat;

public abstract class VehiclesImpl implements Vehicles {
    private double fuelQuantity;
    private double consumption;
    private double tankCapacity;

    protected VehiclesImpl(double fuelQuantity, double consumption,double tankCapacity) {
        this.fuelQuantity = fuelQuantity;
        this.setConsumption(consumption);
        this.tankCapacity=tankCapacity;
    }

    public double getFuelQuantity() {
        return fuelQuantity;
    }

    public void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public double getConsumption() {
        return consumption;
    }

    @Override
    public String drive(double distance) {
        double neededFuel = this.consumption * distance;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String result = String.format("%s needs refueling", this.getClass().getSimpleName());

        if (this.fuelQuantity >= neededFuel) {
            result = String.format("%s travelled %s km",
                    this.getClass().getSimpleName(),
                    decimalFormat.format(distance));
            this.fuelQuantity -= neededFuel;
        }
        return result;
    }

    @Override
    public void refuel(Double liters) {
        if(liters <= 0 ) {
            throw new IllegalArgumentException("Fuel must be a positive number");
        }
        if(this.fuelQuantity+liters>this.tankCapacity){
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        }
            this.fuelQuantity += liters;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f ", this.getClass().getSimpleName(),
                this.fuelQuantity);
    }
}
