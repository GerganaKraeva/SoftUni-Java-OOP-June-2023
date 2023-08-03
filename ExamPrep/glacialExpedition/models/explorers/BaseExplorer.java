package glacialExpedition.models.explorers;

import glacialExpedition.models.suitcases.Carton;
import glacialExpedition.models.suitcases.Suitcase;

import java.util.stream.Collectors;

import static glacialExpedition.common.ConstantMessages.*;
import static glacialExpedition.common.ExceptionMessages.*;

public abstract class BaseExplorer implements Explorer{
    private String name;
    private double energy;
    private Suitcase suitcase;
    private static final double DECREASE_UNITS_ENERGY=15;

    protected BaseExplorer(String name, double energy) {
        this.setName(name);
        this.setEnergy(energy);
        this.suitcase=new Carton();
    }

    @Override
    public String getName() {
        return name;
    }

    private void setName(String name) {
        if(name==null || name.trim().isEmpty()){
            throw new NullPointerException(EXPLORER_NAME_NULL_OR_EMPTY );
        }
        this.name = name;
    }

    @Override
    public double getEnergy() {
        return energy;
    }

    protected void setEnergy(double energy) {
        if(energy<0.0){
            throw new IllegalArgumentException(EXPLORER_ENERGY_LESS_THAN_ZERO);
        }
        this.energy = energy;
    }

    @Override
    public boolean canSearch() {
        return getEnergy()>0;
    }

    @Override
    public Suitcase getSuitcase() {
        return suitcase;
    }

    @Override
    public void search() {
        double currentEnergy=getEnergy()-DECREASE_UNITS_ENERGY;
        if(currentEnergy<0) {
            currentEnergy=0;
        }
        setEnergy(currentEnergy);
    }

    @Override
    public String toString() {
        String exhibits=suitcase.getExhibits().isEmpty()
                ?"None"
                :suitcase.getExhibits().stream().collect(Collectors.joining(FINAL_EXPLORER_SUITCASE_EXHIBITS_DELIMITER));
        return String.format(FINAL_EXPLORER_NAME,name)+System.lineSeparator()+
                String.format(FINAL_EXPLORER_ENERGY,energy)+System.lineSeparator()+
                String.format(FINAL_EXPLORER_SUITCASE_EXHIBITS,exhibits);
    }
}
