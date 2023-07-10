package vehiclesExtension;

public class Bus extends VehiclesImpl {


    private boolean isEmpty;
    private static final boolean EMPTY=true;
    private static final double AC_ADDITIONAL_CONSUMPTION = 1.4;

    public Bus(double fuelQuantity, double consumption, double tankCapacity) {
        super(fuelQuantity, consumption, tankCapacity);
        this.isEmpty = EMPTY;
    }

    public void setEmpty(boolean empty) {
        if (this.isEmpty != empty) {
            super.setConsumption(super.getConsumption() + AC_ADDITIONAL_CONSUMPTION);
        }
    }
}

