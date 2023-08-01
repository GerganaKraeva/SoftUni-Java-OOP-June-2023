package catHouse.entities.cat;

public class ShorthairCat extends BaseCat{
    private static final int KG=7;

    public ShorthairCat(String name, String breed, double price) {
        super(name, breed, price);
        super.setKilograms(KG);
    }

    @Override
    public void eating() {
        super.setKilograms(getKilograms() - 1);

    }
}
