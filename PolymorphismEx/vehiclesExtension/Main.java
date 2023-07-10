package vehiclesExtension;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] vehicleInfo = scanner.nextLine().split("\\s+");
        VehiclesImpl car = getVehicles(vehicleInfo);
        vehicleInfo = scanner.nextLine().split("\\s+");
        VehiclesImpl truck = getVehicles(vehicleInfo);
        vehicleInfo = scanner.nextLine().split("\\s+");
        VehiclesImpl bus = getVehicles(vehicleInfo);

        Map<String, VehiclesImpl> vehicleMap = new LinkedHashMap<>();
        vehicleMap.put("Car", car);
        vehicleMap.put("Truck", truck);
        vehicleMap.put("Bus", bus);

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] command = scanner.nextLine().split("\\s+");
            String commandName = command[0];
            String vehicleType = command[1];
            double argument = Double.parseDouble(command[2]);
            VehiclesImpl vehicle = vehicleMap.get(vehicleType);
            try {
                switch (commandName) {
                    case "Refuel":
                        vehicleMap.get(vehicleType).refuel(argument);
                        break;
                    case "Drive":
                        if (vehicle instanceof Bus) {
                            ((Bus) vehicle).setEmpty(false);
                        }
                        System.out.println(vehicle.drive(argument));
                        break;
                    case "DriveEmpty":
                        System.out.println(vehicle.drive(argument));
                        break;
                    default:
                        throw new IllegalArgumentException("No such command");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
        vehicleMap.values().forEach(System.out::println);
    }

    private static VehiclesImpl getVehicles(String[] vehicleInfo) {
        String vehicleType = vehicleInfo[0];
        double fuelQuantity = Double.parseDouble(vehicleInfo[1]);
        double consumption = Double.parseDouble(vehicleInfo[2]);
        double tankCapacity = Double.parseDouble(vehicleInfo[3]);
        VehiclesImpl vehicles = null;
        switch (vehicleType) {
            case "Car":
                vehicles = new Car(fuelQuantity, consumption, tankCapacity);
                break;
            case "Truck":
                vehicles = new Truck(fuelQuantity, consumption, tankCapacity);
                break;
            case "Bus":
                vehicles = new Bus(fuelQuantity, consumption, tankCapacity);
                break;
        }
        return vehicles;
    }
}