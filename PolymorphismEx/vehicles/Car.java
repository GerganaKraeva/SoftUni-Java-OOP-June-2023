package vehicles;

public class Car extends VehiclesImpl implements Vehicles{
    public Car(double fuelQuantity, double consumption) {
        super(fuelQuantity, consumption);
        super.setConsumption(consumption+0.9);
    }

}
