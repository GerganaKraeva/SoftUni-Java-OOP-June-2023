package vehicles;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        VehiclesImpl car = getVehicles(scanner);
        VehiclesImpl truck = getVehicles(scanner);

        Map<String, VehiclesImpl> vehicleMap = new LinkedHashMap<>();
        vehicleMap.put("Car", car);
        vehicleMap.put("Truck", truck);

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 1; i <= n; i++) {
            String[] command = scanner.nextLine().split("\\s+");
            String commandName = command[0];
            String vehicleType = command[1];
            double argument = Double.parseDouble(command[2]);

            if (commandName.equals("Refuel")) {
                vehicleMap.get(vehicleType).refuel(argument);
            } else if ("Drive".equals(commandName)) {
                System.out.println(vehicleMap.get(vehicleType).drive(argument));
            }
        }
       vehicleMap.values().forEach(System.out::println);
    }


    private static VehiclesImpl getVehicles(Scanner scanner) {
        String [] vehicleInfo=scanner.nextLine().split("\\s+");
        String vehicleType = vehicleInfo[0];
        double fuelQuantity = Double.parseDouble(vehicleInfo[1]);
        double consumption = Double.parseDouble(vehicleInfo[2]);

        switch (vehicleType) {
            case "Car":
                return  new Car(fuelQuantity, consumption);
            case "Truck":
                return new Truck(fuelQuantity, consumption);
            default:
                throw new IllegalArgumentException("Missing car");
        }
    }


}
