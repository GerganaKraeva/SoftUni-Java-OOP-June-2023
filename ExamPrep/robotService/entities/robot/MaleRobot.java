package robotService.entities.robot;

public class MaleRobot extends BaseRobot{

    private static final int KILOGRAMS=9;

    public MaleRobot(String name, String kind, double price) {
        super(name, kind, KILOGRAMS, price);
    }

    @Override
    public void eating() {
        int currentKg=getKilograms();
        int increaseKg=3;
        super.setKilograms(currentKg+increaseKg);
    }
}
