package vehicles;

public class Truck extends VehiclesImpl {
    private static final double AC_ADDITIONAL_CONSUMPTION = 1.6;
    private static final double FUEL_AFTER_DRIVER_DEDUCTION = 0.95;

    public Truck(double fuelQuantity, double consumption) {
        super(fuelQuantity, consumption + AC_ADDITIONAL_CONSUMPTION);
    }

    @Override
    public void refuel(Double liters) {
        super.refuel(FUEL_AFTER_DRIVER_DEDUCTION * liters);
    }
}
