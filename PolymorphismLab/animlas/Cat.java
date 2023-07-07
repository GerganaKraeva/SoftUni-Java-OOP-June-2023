package animlas;

public class Cat extends Animal{
    public Cat(String name, String favouriteFood) {
        super(name, favouriteFood);
    }

    @Override
    public String explainSelf() {
        StringBuilder sb=new StringBuilder();
        sb.append(String.format("I am %s and my favourite food is %s",super.getName(),super.getFavouriteFood()))
                .append(System.lineSeparator())
                .append("MEEOW");
        return sb.toString();
    }
}
