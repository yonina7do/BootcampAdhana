package com.yoninaldo.dealership;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private Scanner scanner = new Scanner(System.in);

    public UserInterface() {

    }

    //main menu display
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
                    System.out.println("Invalid choice. Please try again.");
            }

            if (!exit) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
                System.out.println("-------------------------------");
            }
        }

        scanner.close();
    }

    //initialize dealership from the file
    private void init() {
        DealershipFileManager fileManager = new DealershipFileManager();
        this.dealership = fileManager.getDealership();

        System.out.println("Welcome to " + dealership.getName() + "!");
        System.out.println("Located at: " + dealership.getAddress());
        System.out.println("Phone: " + dealership.getPhone());
        System.out.println("-------------------------------");
    }

    //main menu options
    private void displayMenu() {
        System.out.println("What would you like to do?");
        System.out.println("1 - Find vehicles within a price range");
        System.out.println("2 - Find vehicles by make/model");
        System.out.println("3 - Find vehicles by year range");
        System.out.println("4 - Find vehicles by color");
        System.out.println("5 - Find vehicles by mileage range");
        System.out.println("6 - Find vehicles by type (car, truck, SUV, van)");
        System.out.println("7 - List ALL vehicles");
        System.out.println("8 - Add a vehicle");
        System.out.println("9 - Remove a vehicle");
        System.out.println("0 - Quit");
    }

    //helper method to display vehicle list
    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found matching your criteria.");
            return;
        }

        System.out.println("\nVehicles Found:");
        System.out.println("-------------------------------");

        for (Vehicle vehicle : vehicles) {
            System.out.println("VIN: " + vehicle.getVin());
            System.out.println("Make: " + vehicle.getMake());
            System.out.println("Model: " + vehicle.getModel());
            System.out.println("Year: " + vehicle.getYear());
            System.out.println("Type: " + vehicle.getVehicleType());
            System.out.println("Color: " + vehicle.getColor());
            System.out.println("Mileage: " + vehicle.getOdometer());
            System.out.println("Price: $" + vehicle.getPrice());
            System.out.println("-------------------------------");
        }

        System.out.println("Total vehicles: " + vehicles.size());
    }

    //to process request for all vehicles
    public void processGetAllVehiclesRequest() {
        System.out.println("\nListing all vehicles:");
        List<Vehicle> allVehicles = dealership.getAllVehicles();
        displayVehicles(allVehicles);
    }

    //Empty methods to be implemented
    public void processGetByPriceRequest() {

    }

    public void processGetByMakeModelRequest() {

    }

    public void processGetByYearRequest() {

    }

    public void processGetByColorRequest() {

    }

    public void processGetByMileageRequest() {

    }

    public void processGetByVehicleTypeRequest() {

    }

    public void processAddVehicleRequest() {

    }

    public void processRemoveVehicleRequest() {

    }
}