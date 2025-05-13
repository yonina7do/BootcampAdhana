package com.yoninaldo.dealership;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private Scanner scanner = new Scanner(System.in);
    private DealershipFileManager fileManager = new DealershipFileManager();

    public UserInterface() {
    }


    public void display() {
        init();

        boolean exit = false;
        while (!exit) {
            displayMenu();

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    processGetByPriceRequest();
                    break;
                case 2:
                    processGetByMakeModelRequest();
                    break;
                case 3:
                    processGetByYearRequest();
                    break;
                case 4:
                    processGetByColorRequest();
                    break;
                case 5:
                    processGetByMileageRequest();
                    break;
                case 6:
                    processGetByVehicleTypeRequest();
                    break;
                case 7:
                    processGetAllVehiclesRequest();
                    break;
                case 8:
                    processAddVehicleRequest();
                    break;
                case 9:
                    processRemoveVehicleRequest();
                    break;
                case 0:
                    exit = true;
                    System.out.println("Thank you for using the Dealership App. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

            if (!exit) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
                System.out.println("-------------------------------");
            }
        }
        scanner.close();
    }

    //initialize dealership
    private void init() {
        this.dealership = fileManager.getDealership();

        System.out.println("Welcome to " + dealership.getName() + "!");
        System.out.println("Located at: " + dealership.getAddress());
        System.out.println("Phone: " + dealership.getPhone());
        System.out.println("-------------------------------");
    }

    private void displayMenu() {
        System.out.println("\n┌────────────────────────────────────────┐");
        System.out.println("│       YONINALDO'S LUXURY MOTORS        │");
        System.out.println("├────────────────────────────────────────┤");
        System.out.println("│ 1 - Find vehicles within a price range │");
        System.out.println("│ 2 - Find vehicles by make/model        │");
        System.out.println("│ 3 - Find vehicles by year range        │");
        System.out.println("│ 4 - Find vehicles by color             │");
        System.out.println("│ 5 - Find vehicles by mileage range     │");
        System.out.println("│ 6 - Find vehicles by type              │");
        System.out.println("│ 7 - List ALL vehicles                  │");
        System.out.println("│ 8 - Add a vehicle                      │");
        System.out.println("│ 9 - Remove a vehicle                   │");
        System.out.println("│ 0 - Quit                               │");
        System.out.println("└────────────────────────────────────────┘");
    }

    //to show a vehicle list
    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }

        System.out.println("\nVehicles Found: " + vehicles.size());
        System.out.println("-------------------------------");

        for (Vehicle vehicle : vehicles) {
            System.out.println("VIN: " + vehicle.getVin());
            System.out.println("Year: " + vehicle.getYear());
            System.out.println("Make: " + vehicle.getMake());
            System.out.println("Model: " + vehicle.getModel());
            System.out.println("Type: " + vehicle.getVehicleType().getDisplayName());
            System.out.println("Color: " + vehicle.getColor());
            System.out.println("Mileage: " + vehicle.getOdometer());
            System.out.println("Price: $" + vehicle.getPrice());
            System.out.println("-------------------------------");
        }
    }

    public void processGetAllVehiclesRequest() {
        System.out.println("\nAll vehicles:");
        List<Vehicle> allVehicles = dealership.getAllVehicles();
        displayVehicles(allVehicles);
    }

    public void processGetByPriceRequest() {
        System.out.println("\nFind by price range:");

        System.out.print("Minimum price: $");
        double minPrice = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Maximum price: $");
        double maxPrice = scanner.nextDouble();
        scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByPrice(minPrice, maxPrice);
        displayVehicles(vehicles);
    }

    public void processGetByMakeModelRequest() {
        System.out.println("\nFind by make and model:");

        System.out.print("Make: ");
        String make = scanner.nextLine();

        System.out.print("Model: ");
        String model = scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(vehicles);
    }

    public void processGetByYearRequest() {
        System.out.println("\nFind by year range:");

        System.out.print("Minimum year: ");
        int minYear = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Maximum year: ");
        int maxYear = scanner.nextInt();
        scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByYear(minYear, maxYear);
        displayVehicles(vehicles);
    }

    public void processGetByColorRequest() {
        System.out.println("\nFind by color:");

        System.out.print("Color: ");
        String color = scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByColor(color);
        displayVehicles(vehicles);
    }

    public void processGetByMileageRequest() {
        System.out.println("\nFind by mileage range:");

        System.out.print("Minimum mileage: ");
        int minMileage = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Maximum mileage: ");
        int maxMileage = scanner.nextInt();
        scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByMileage(minMileage, maxMileage);
        displayVehicles(vehicles);
    }

    public void processGetByVehicleTypeRequest() {
        System.out.println("\nFind by type:");

        System.out.println("Available types:");
        for (VehicleType type : VehicleType.values()) {
            System.out.println("- " + type.getDisplayName());
        }

        System.out.print("Type: ");
        String typeInput = scanner.nextLine();

        VehicleType selectedType = VehicleType.fromString(typeInput);

        List<Vehicle> vehicles = dealership.getVehiclesByType(selectedType);
        displayVehicles(vehicles);
    }

    //add vehicle
    public void processAddVehicleRequest() {
        System.out.println("\nAdd new vehicle:");

        try {
            System.out.print("VIN: ");
            int vin = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Year: ");
            int year = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Make: ");
            String make = scanner.nextLine();

            System.out.print("Model: ");
            String model = scanner.nextLine();

            System.out.println("Available types:");
            for (VehicleType type : VehicleType.values()) {
                System.out.println("- " + type.getDisplayName());
            }

            System.out.print("Type: ");
            String typeString = scanner.nextLine();
            VehicleType vehicleType = VehicleType.fromString(typeString);

            System.out.print("Color: ");
            String color = scanner.nextLine();

            System.out.print("Odometer: ");
            int odometer = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Price: $");
            double price = scanner.nextDouble();
            scanner.nextLine();

            Vehicle newVehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
            dealership.addVehicle(newVehicle);

            fileManager.saveDealership(dealership);

            System.out.println("\nVehicle added!");

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    //to remove avehicle
    public void processRemoveVehicleRequest() {
        System.out.println("\nRemove vehicle:");

        try {
            processGetAllVehiclesRequest();

            System.out.print("\nVIN to remove: ");
            int vinToRemove = scanner.nextInt();
            scanner.nextLine();

            Vehicle vehicleToRemove = null;
            for (Vehicle vehicle : dealership.getAllVehicles()) {
                if (vehicle.getVin() == vinToRemove) {
                    vehicleToRemove = vehicle;
                    break;
                }
            }

            if (vehicleToRemove != null) {
                System.out.println("\nFound vehicle:");
                System.out.println("VIN: " + vehicleToRemove.getVin());
                System.out.println("Year: " + vehicleToRemove.getYear());
                System.out.println("Make: " + vehicleToRemove.getMake());
                System.out.println("Model: " + vehicleToRemove.getModel());

                System.out.print("\nConfirm removal (y/n): ");
                String confirmation = scanner.nextLine();

                if (confirmation.equalsIgnoreCase("y")) {
                    dealership.removeVehicle(vehicleToRemove);

                    fileManager.saveDealership(dealership);

                    System.out.println("Vehicle removed!");
                } else {
                    System.out.println("Removal cancelled.");
                }
            } else {
                System.out.println("No vehicle found with VIN: " + vinToRemove);
            }

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());

        }
    }
}
