package cardsWithPower;

public enum SuitPowers {
    CLUBS (0), DIAMONDS (13), HEARTS(26), SPADES (39);
    private int powerOfSuits;

    SuitPowers(int powerOfSuits) {
        this.powerOfSuits = powerOfSuits;
    }

    public int getPowerOfSuits() {
        return powerOfSuits;
    }
}
