package birthdayCelebrations;

public class Pets implements Birthable{
    String name;
    String birthday;

    public Pets(String name, String birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getBirthDate() {
        return birthday;
    }
}
