package com.yoninaldo.dealership;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DealershipFileManager {

    public Dealership getDealership() {
        Dealership dealership = null;

        try {
            //open and read file
            FileReader fileReader = new FileReader("inventory.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //to read first line dealership info
            String line = bufferedReader.readLine();
            String[] dealershipData = line.split("\\|");

            //create dealership
            String name = dealershipData[0];
            String address = dealershipData[1];
            String phone = dealershipData[2];
            dealership = new Dealership(name, address, phone);

            //to read vehicle data
            line = bufferedReader.readLine();
            while (line != null) {
                String[] vehicleData = line.split("\\|");

                int vin = Integer.parseInt(vehicleData[0]);
                int year = Integer.parseInt(vehicleData[1]);
                String make = vehicleData[2];
                String model = vehicleData[3];
                String vehicleType = vehicleData[4];
                String color = vehicleData[5];
                int odometer = Integer.parseInt(vehicleData[6]);
                double price = Double.parseDouble(vehicleData[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                dealership.addVehicle(vehicle);

                line = bufferedReader.readLine();
            }

            bufferedReader.close();

        } catch (IOException e) {
            System.out.println("Error reading the file!");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Something went wrong!");
            e.printStackTrace();
        }

        return dealership;
    }

    public void saveDealership(Dealership dealership) {
    }
}