package com.yoninaldo.dealership;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DealershipFileManager {
    public Dealership getDealership() {
        Dealership dealership = null;

        try {
            FileReader fileReader = new FileReader("src/main/resources/inventory.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            String[] dealershipData = line.split("\\|");

            //dealership object
            String name = dealershipData[0];
            String address = dealershipData[1];
            String phone = dealershipData[2];
            dealership = new Dealership(name, address, phone);

            //Read vehicle data from remaining lines
            line = bufferedReader.readLine();
            while (line != null) {
                String[] vehicleData = line.split("\\|");

                //parse vehicle data
                int vin = Integer.parseInt(vehicleData[0]);
                int year = Integer.parseInt(vehicleData[1]);
                String make = vehicleData[2];
                String model = vehicleData[3];
                VehicleType vehicleType = VehicleType.fromString(vehicleData[4]);
                String color = vehicleData[5];
                int odometer = Integer.parseInt(vehicleData[6]);
                double price = Double.parseDouble(vehicleData[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                dealership.addVehicle(vehicle);

                line = bufferedReader.readLine();
            }

            bufferedReader.close();

        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
            dealership = new Dealership("yoninaldo's Luxury Motors", "888 Elite Boulevard", "555-787-9900");
        } catch (Exception ex) {
            System.out.println("Error processing data: " + ex.getMessage());
            dealership = new Dealership("yoninaldo's Luxury Motors", "888 Elite Boulevard", "555-787-9900");
        }

        //ensures we never return null
        if (dealership == null) {
            dealership = new Dealership("yoninaldo's Luxury Motors", "888 Elite Boulevard", "555-787-9900");
        }

        return dealership;
    }

    //save dealearship to file
    public void saveDealership(Dealership dealership) {
        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/inventory.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String dealershipLine = dealership.getName() + "|" +
                    dealership.getAddress() + "|" +
                    dealership.getPhone();
            bufferedWriter.write(dealershipLine);
            bufferedWriter.newLine();

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

            bufferedWriter.close();
            System.out.println("Dealership saved!");

        } catch (IOException ex) {
            System.out.println("Error saving file: " + ex.getMessage());
        }
    }
}