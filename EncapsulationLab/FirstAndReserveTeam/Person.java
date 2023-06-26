package firstAndReserveTeam;

import java.text.DecimalFormat;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private double salary;

    public Person(String firstName, String lastName, int age) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAge(age);
    }

    public Person(String firstName, String lastName, int age, double salary) {
        this(firstName, lastName, age);
        this.setSalary(salary);
    }

    private String getFirstName() {
        return firstName;
    }

    private String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    private double getSalary() {
        return salary;
    }

    private void setSalary(double salary) {
        if(salary<460.0){
            throw new IllegalArgumentException("Salary cannot be less than 460 leva");
        }else{
            this.salary=salary;
        }
    }

    public void setAge(int age) {
        if(age<=0){
            throw new IllegalArgumentException("Age cannot be zero or negative integer");
        }else {
            this.age = age;
        }
    }

    public void setFirstName(String firstName) {
        if (firstName!=null && firstName.length() < 3) {
            throw new IllegalArgumentException("First name cannot be less than 3 symbols");
        } else {
            this.firstName = firstName;
        }
    }

    public void setLastName(String lastName) {
        if (lastName!=null && lastName.length() < 3) {
            throw new IllegalArgumentException("Last name cannot be less than 3 symbols");
        } else {
            this.lastName = firstName;
        }
    }

    public void increaseSalary(double bonus) {
        bonus = bonus / 100;
        double modifier = 1;
        if (this.getAge() < 30) {
            modifier += bonus / 2;
        } else {
            modifier += bonus;
        }
        this.setSalary(this.getSalary() * modifier);
    }

    @Override
    public String toString() {
        return String.format("%S %s gets %s leva\n",
                this.getFirstName(),
                this.getLastName(),
               new DecimalFormat("#.0####").format(this.getSalary()));
    }
}
