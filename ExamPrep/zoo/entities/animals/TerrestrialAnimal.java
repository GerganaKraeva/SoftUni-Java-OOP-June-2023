package zoo.entities.animals;

public class TerrestrialAnimal extends BaseAnimal {

    private static final double KILOGRAMS = 5.50;

    public TerrestrialAnimal(String name, String kind, double price) {
        super(name, kind, KILOGRAMS, price);
    }

    @Override
    public void eat() {
        double currentKg = getKg() + 5.7;
        setKg(currentKg);
    }
}



