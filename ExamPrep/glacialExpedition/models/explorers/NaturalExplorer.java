package glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer {

    private static final double UNITS_OF_ENERGY = 60;

    public NaturalExplorer(String name) {
        super(name, UNITS_OF_ENERGY);
    }

    @Override
    public void search() {
        double currentEnergy = getEnergy() - 7;
        if (currentEnergy < 0) {
            currentEnergy = 0;
        }
        setEnergy(currentEnergy);
    }
}
