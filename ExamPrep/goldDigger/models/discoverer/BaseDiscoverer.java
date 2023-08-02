package goldDigger.models.discoverer;


import goldDigger.models.museum.BaseMuseum;
import goldDigger.models.museum.Museum;

import static goldDigger.common.ExceptionMessages.*;

public abstract class BaseDiscoverer implements Discoverer {

    private String name;
    private double energy;
    private Museum museum;
    private static final int ENERGY_COST_OF_DIGGING = 15;

    protected BaseDiscoverer(String name, double energy) {
        this.setName(name);
        this.setEnergy(energy);
        this.museum =new BaseMuseum();
    }

    @Override
    public String getName() {
        return name;
    }

    protected void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(DISCOVERER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public double getEnergy() {
        return energy;
    }

    protected void setEnergy(double energy) {
        if (energy < 0) {
            throw new IllegalArgumentException(DISCOVERER_ENERGY_LESS_THAN_ZERO);
        }
        this.energy = energy;
    }

    @Override
    public void dig() {
        double newEnergy = getEnergy() - ENERGY_COST_OF_DIGGING;
        if (newEnergy < 0) {
           newEnergy=0;
        }
        setEnergy(newEnergy);
    }

    @Override
    public boolean canDig() {
        double currentEnergy = getEnergy();
        boolean canDig = 0 < currentEnergy;
        return canDig;
    }

    @Override
    public Museum getMuseum() {
        return museum;
    }
}
