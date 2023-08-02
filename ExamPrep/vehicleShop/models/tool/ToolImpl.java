package vehicleShop.models.tool;

import static vehicleShop.common.ExceptionMessages.*;

public class ToolImpl implements Tool{

    private int power;

    public ToolImpl(int power) {
        this.setPower(power);
    }

    @Override
    public int getPower() {
        return power;
    }

    protected void setPower(int power) {
        if(power<0){
            throw new IllegalArgumentException(TOOL_POWER_LESS_THAN_ZERO);
        }
        this.power = power;
    }

    @Override
    public void decreasesPower() {
        int decreasePower = getPower() - 5;
        if (decreasePower < 0) {
            decreasePower=0;
        }
            setPower(decreasePower);
    }

    @Override
    public boolean isUnfit() {
        return getPower()>0;
    }
}
