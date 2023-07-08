package vehicles;

import java.text.DecimalFormat;

public abstract class VehiclesImpl implements Vehicles {
    private double fuelQuantity;
    private double consumption;

    public void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    protected VehiclesImpl(double fuelQuantity, double consumption) {
        this.setFuelQuantity(fuelQuantity);
        this.setConsumption(consumption);
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
        this.fuelQuantity+=liters;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f ",this.getClass().getSimpleName(),
                this.fuelQuantity);
    }
}
