package vehiclesExtension;

public class Car extends VehiclesImpl {
    private static final double AC_ADDITIONAL_CONSUMPTION=0.9;
    public Car(double fuelQuantity, double consumption,double tankCapacity) {
        super(fuelQuantity, consumption+AC_ADDITIONAL_CONSUMPTION,tankCapacity);
    }

}
