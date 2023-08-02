package vehicleShop.models.vehicle;

import static vehicleShop.common.ExceptionMessages.*;

public class VehicleImpl implements Vehicle {
    private String name;
    private int strengthRequired;

    public VehicleImpl(String name, int strengthRequired) {
        this.setName(name);
        this.setStrengthRequired(strengthRequired);
    }

    @Override
    public String getName() {
        return name;
    }

    protected void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(VEHICLE_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int getStrengthRequired() {
        return strengthRequired;
    }

    protected void setStrengthRequired(int strengthRequired) {
        if (strengthRequired < 0) {
            throw new IllegalArgumentException(VEHICLE_STRENGTH_LESS_THAN_ZERO);
        }
        this.strengthRequired = strengthRequired;
    }

    @Override
    public boolean reached() {
        return getStrengthRequired() == 0;
    }

    @Override
    public void making() {
        int currentRequiredStrength = getStrengthRequired() - 5;
        if (currentRequiredStrength < 0) {
            currentRequiredStrength = 0;
        }
        setStrengthRequired(currentRequiredStrength);
    }
}
