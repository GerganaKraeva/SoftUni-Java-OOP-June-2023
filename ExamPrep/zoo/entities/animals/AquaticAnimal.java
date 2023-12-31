package zoo.entities.animals;

public  class AquaticAnimal extends BaseAnimal{
    private static final double KILOGRAMS=2.50;

    public AquaticAnimal(String name, String kind, double price) {
        super(name, kind, KILOGRAMS, price);
    }

    @Override
    public void eat() {
        double currentKg = getKg() + 7.5;
        setKg(currentKg);
    }
}
