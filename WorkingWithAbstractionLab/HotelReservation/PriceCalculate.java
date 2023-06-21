package HotelReservation;

public class PriceCalculate {
    private double pricePerDay;
    private int days;
    private Season season;
    private DiscountType discountType;

    public PriceCalculate(double pricePerDay, int days, Season season, DiscountType discountType) {
        this.pricePerDay = pricePerDay;
        this.days = days;
        this.season = season;
        this.discountType = discountType;
    }
    public void calculate(){
        double result=this.pricePerDay*this.days*this.season.getPriceModifier()*this.discountType.getDiscount();
        System.out.printf("%.2f\n",result);
    }
}
