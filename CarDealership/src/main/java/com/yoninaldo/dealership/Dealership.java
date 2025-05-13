package com.yoninaldo.dealership;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    // Constructor
    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //methods for vehicle list
    public List<Vehicle> getAllVehicles() {
        return inventory;
    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    //methods for search
    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        List<Vehicle> matchingVehicles = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            double price = vehicle.getPrice();
            if (price >= min && price <= max) {
                matchingVehicles.add(vehicle);
            }
        }

        return matchingVehicles;
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        List<Vehicle> matchingVehicles = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)) {
                matchingVehicles.add(vehicle);
            }
        }

        return matchingVehicles;
    }

    public List<Vehicle> getVehiclesByYear(int min, int max) {
        List<Vehicle> matchingVehicles = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            int year = vehicle.getYear();
            if (year >= min && year <= max) {
                matchingVehicles.add(vehicle);
            }
        }

        return matchingVehicles;
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> matchingVehicles = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                matchingVehicles.add(vehicle);
            }
        }

        return matchingVehicles;
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        List<Vehicle> matchingVehicles = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            int odometer = vehicle.getOdometer();
            if (odometer >= min && odometer <= max) {
                matchingVehicles.add(vehicle);
            }
        }

        return matchingVehicles;
    }

    public List<Vehicle> getVehiclesByType(VehicleType vehicleType) {
        List<Vehicle> matchingVehicles = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getVehicleType() == vehicleType) {
                matchingVehicles.add(vehicle);
            }
        }

        return matchingVehicles;
    }

    public List<Vehicle> getVehiclesByType(String vehicleTypeString) {
        VehicleType type = VehicleType.fromString(vehicleTypeString);
        return getVehiclesByType(type);
    }

    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);
    }
}