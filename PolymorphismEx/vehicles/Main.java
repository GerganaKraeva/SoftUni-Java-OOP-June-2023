package vehicles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] carInfo = scanner.nextLine().split("\\s+");
        double fuelQuantity = Double.parseDouble(carInfo[1]);
        double consumption = Double.parseDouble(carInfo[2]);
        VehiclesImpl car = new Car(fuelQuantity, consumption);
        String[] truckInfo = scanner.nextLine().split("\\s+");
        double fuelQuantityT = Double.parseDouble(truckInfo[1]);
        double consumptionT = Double.parseDouble(truckInfo[2]);
        VehiclesImpl truck = new Truck(fuelQuantityT, consumptionT);

        int n=Integer.parseInt(scanner.nextLine());
        for (int i = 1; i <=n ; i++) {
            String [] command=scanner.nextLine().split("\\s+");
            if(DriveOrRefuel.valueOf(command[0])==DriveOrRefuel.Refuel){
                double liters=Double.parseDouble(command[2]);
                if(command[1].equals("Car")){
                    car.refuel(liters);
                }else if(command[1].equals("Truck")){
                    truck.refuel(liters);
                }
            }else if(DriveOrRefuel.valueOf(command[0])==DriveOrRefuel.Drive){
                double distance=Double.parseDouble(command[2]);
                if(command[1].equals("Car")){
                    System.out.println(car.drive(distance));
                }else if(command[1].equals("Truck")){
                    System.out.println(truck.drive(distance));
                }
            }
        }
        System.out.println(car);
        System.out.println(truck);
    }
}
