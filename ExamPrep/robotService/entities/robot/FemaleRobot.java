package robotService.entities.robot;

public class FemaleRobot extends BaseRobot{
    private static final int KILOGRAMS=7;

    public FemaleRobot(String name, String kind, double price) {
        super(name, kind, KILOGRAMS, price);
    }

    @Override
    public void eating() {
        int currentKg=getKilograms();
        int increaseKg=1;
        setKilograms(currentKg+increaseKg);
    }
}
