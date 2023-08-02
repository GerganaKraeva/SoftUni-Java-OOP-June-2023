package vehicleShop.models.worker;

import vehicleShop.models.tool.Tool;

import java.util.ArrayList;
import java.util.Collection;

import static vehicleShop.common.ExceptionMessages.*;

public abstract class BaseWorker implements Worker{
private String name;
private int strength;
private Collection<Tool>tools;

    protected BaseWorker(String name, int strength) {
        this.setName(name);
        this.setStrength(strength);
        this.tools=new ArrayList<>();
    }


    @Override
    public void addTool(Tool tool) {
        tools.add(tool);

    }

    @Override
    public boolean canWork() {
        return getStrength()>0;
    }

    @Override
    public String getName() {
        return name;
    }

    protected void setName(String name) {
        if(name==null ||name.trim().isEmpty()) {
            throw new IllegalArgumentException(WORKER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public String toString() {
        long leftTools=this.tools.stream().filter(t->t.getPower()>0).count();
        StringBuilder sb= new StringBuilder();
        sb.append("Name: "+name+", Strength: "+strength).append(System.lineSeparator());
        sb.append("Tools: "+ leftTools+" fit left").append(System.lineSeparator());
    return sb.toString().trim();
    }

    protected void setStrength(int strength) {
        if(strength<0){
            throw new IllegalArgumentException(WORKER_STRENGTH_LESS_THAN_ZERO);
        }
        this.strength = strength;
    }

    @Override
    public void working() {
        int currentStrength=getStrength()-10;
        if(currentStrength<0){
            setStrength(0);
        }else{
            setStrength(currentStrength);
        }
    }

    @Override
    public Collection<Tool> getTools() {
        return tools;
    }
}
