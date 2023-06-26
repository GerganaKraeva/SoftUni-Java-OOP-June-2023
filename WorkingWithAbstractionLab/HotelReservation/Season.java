package hotelReservation;

public enum Season {
    Autumn(1),Spring(2),Winter(3) ,Summer(4);
    private int priceModifier;

    public int getPriceModifier() {
        return priceModifier;
    }

    Season(int priceModifier) {
        this.priceModifier = priceModifier;
    }
}
