package catHouse.entities.cat;

public class LonghairCat extends BaseCat {
    private static final int KG = 9;

    public LonghairCat(String name, String breed, double price) {
        super(name, breed, price);
        super.setKilograms(KG);
    }

    @Override
    public void eating() {
        super.setKilograms(getKilograms() - 3);
    }
}
