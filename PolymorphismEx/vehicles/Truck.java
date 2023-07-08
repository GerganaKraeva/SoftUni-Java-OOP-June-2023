package vehicles;

public class Truck extends VehiclesImpl implements Vehicles {
    public Truck(double fuelQuantity, double consumption) {
        super(fuelQuantity, consumption);
        super.setConsumption(consumption+1.6);
    }

    @Override
    public void refuel(Double liters) {
        super.refuel(0.95*liters);
    }
}
