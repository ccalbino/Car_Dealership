package com.pluralsight;

import java.io.*;

import java.util.regex.Pattern;


public class DealershipFileManager {


    private static String file = "inventory.csv";


    public static Dealership getDealership() {

        try {
            Dealership dealership = null;
            FileReader dealerShip = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(dealerShip);


            String dealerShipCsv;
            dealerShipCsv = bufferedReader.readLine();

            if (dealerShipCsv != null) {
                String[] dealershipCsvParts = dealerShipCsv.split(Pattern.quote("|"));
                String dealerName = dealershipCsvParts[0];
                String dealerAddress = dealershipCsvParts[1];
                String dealerPhone = dealershipCsvParts[2];

                dealership = new Dealership(dealerName, dealerAddress,dealerPhone);
            }

            String inputString;

            while ((inputString = bufferedReader.readLine()) != null) {
                if (inputString.trim().isEmpty()) {
                    continue;
                }

                String[] parts = inputString.split(Pattern.quote("|"));

                int vin = Integer.parseInt(parts[0]);
                int year = Integer.parseInt(parts[1]);
                String make = parts[2];
                String model = parts[3];
                String vehicleType = parts[4];
                String color = parts[5];
                int odometer = Integer.parseInt(parts[6]);
                double price = Double.parseDouble(parts[7]);

                if(dealership != null){
                    dealership.addVehicle(new Vehicle(vin, year, make, model, vehicleType, color, odometer, price));
                }

            }
            return dealership;
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }



    public static void saveDealership(Dealership dealership) {

        try {
            FileWriter dealerShipLog = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(dealerShipLog);

            bufferedWriter.write(dealership.toStringDealership());

            for(Vehicle v : dealership.getAllVehicles()){
                bufferedWriter.write("\n" + v.toString());
            }

            bufferedWriter.close();

        } catch (IOException e) {

            throw new RuntimeException(e);
        }


    }


}
