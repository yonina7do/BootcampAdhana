package com.yoninaldo.dealership;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DealershipFileManager {

    // Read dealership from file
    public Dealership getDealership() {
        Dealership dealership = null;

        try {
            FileReader fileReader = new FileReader("src/main/resources/inventory.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Read dealership info from first line
            String line = bufferedReader.readLine();
            String[] dealershipData = line.split("\\|");

            // Create dealership object
            String name = dealershipData[0];
            String address = dealershipData[1];
            String phone = dealershipData[2];
            dealership = new Dealership(name, address, phone);

            // Read vehicle data from remaining lines
            line = bufferedReader.readLine();
            while (line != null) {
                String[] vehicleData = line.split("\\|");

                // Parse vehicle data
                int vin = Integer.parseInt(vehicleData[0]);
                int year = Integer.parseInt(vehicleData[1]);
                String make = vehicleData[2];
                String model = vehicleData[3];
                VehicleType vehicleType = VehicleType.fromString(vehicleData[4]);
                String color = vehicleData[5];
                int odometer = Integer.parseInt(vehicleData[6]);
                double price = Double.parseDouble(vehicleData[7]);

                // Create vehicle and add to dealership
                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                dealership.addVehicle(vehicle);

                // Read next line
                line = bufferedReader.readLine();
            }

            // Close file
            bufferedReader.close();

        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
            dealership = new Dealership("Yoninaldo's Luxury Motors", "888 Elite Boulevard", "555-787-9900");
        } catch (Exception ex) {
            System.out.println("Error processing data: " + ex.getMessage());
            dealership = new Dealership("Yoninaldo's Luxury Motors", "888 Elite Boulevard", "555-787-9900");
        }

        // Ensure we never return null
        if (dealership == null) {
            dealership = new Dealership("Yoninaldo's Luxury Motors", "888 Elite Boulevard", "555-787-9900");
        }

        return dealership;
    }

    // Save dealership to file
    public void saveDealership(Dealership dealership) {
        try {
            // Open file for writing
            FileWriter fileWriter = new FileWriter("src/main/resources/inventory.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Write dealership info to first line
            String dealershipLine = dealership.getName() + "|" +
                    dealership.getAddress() + "|" +
                    dealership.getPhone();
            bufferedWriter.write(dealershipLine);
            bufferedWriter.newLine();

            // Write each vehicle on its own line
            for (Vehicle vehicle : dealership.getAllVehicles()) {
                String vehicleLine = vehicle.getVin() + "|" +
                        vehicle.getYear() + "|" +
                        vehicle.getMake() + "|" +
                        vehicle.getModel() + "|" +
                        vehicle.getVehicleType().toString() + "|" +
                        vehicle.getColor() + "|" +
                        vehicle.getOdometer() + "|" +
                        vehicle.getPrice();
                bufferedWriter.write(vehicleLine);
                bufferedWriter.newLine();
            }

            // Close file
            bufferedWriter.close();
            System.out.println("Dealership saved!");

        } catch (IOException ex) {
            System.out.println("Error saving file: " + ex.getMessage());
        }
    }
}