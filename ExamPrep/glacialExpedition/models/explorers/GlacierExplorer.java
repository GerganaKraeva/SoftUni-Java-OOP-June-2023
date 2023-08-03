package glacialExpedition.models.explorers;

public class GlacierExplorer extends BaseExplorer{

    private static final double UNITS_OF_ENERGY=40;

    public GlacierExplorer(String name) {
        super(name, UNITS_OF_ENERGY);
    }
}
