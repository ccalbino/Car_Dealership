package com.pluralsight;

import java.util.ArrayList;

public class AdminUserInterface {

    private Dealership d;
    private ArrayList<Contract> c = new ArrayList<>();
    private final Console console = new Console();


    private void init() {
        c = com.pluralsight.ContractFileManager.getContracts();
        d = DealershipFileManager.getDealership();


    }

    public void displayMenu() {
        init();

        display();
    }

    private void displayContracts(ArrayList<Contract> contracts) {


        for (Contract c : contracts) {
            System.out.println(c);
        }


    }

    private void displayAllContacts() {
        displayContracts(c);


    }


    private void display() {

        int input;
        String homeScreenPrompt = """
                Welcome Admin What do you want to do:\s
                 1 - Make Vehicle Lease\s
                 2 - Make Vehicle Sale\s
                 3 - Display All\s
                 4 - Display Leases\s
                 5 - Display Sales\s
                 0 - Quit
                Enter your command(number 1-9):\s""";


        do {
            input = console.promptForInt(homeScreenPrompt);
            switch (input) {
                case 1:
                    processVehicleByLease();
                    break;
                case 2:
                    processVehicleBySale();
                    break;
                case 3:
                    displayAllContacts();
                    break;
                case 4:
                    displayLease(c);
                    break;
                case 5:
                    displaySales(c);
                    break;
                case 0:
                    System.out.println("Quitting...");
                    break;
                default:
                    System.out.println("Not a valid input");
                    break;
            }


        } while (input != 0);

    }


    private void displayLease(ArrayList<Contract> contracts) {

        System.out.println(LeaseContract.getFormattedHeader());
        for (Contract contract : contracts) {
            if (contract instanceof LeaseContract) {
                System.out.println(contract);
            }
        }


    }

    private void displaySales(ArrayList<Contract> contracts) {
        System.out.println(SalesContract.getFormattedHeader());
        for (Contract contract : contracts) {
            if (contract instanceof SalesContract) {
                System.out.println(contract);
            }
        }


    }

    private void processVehicleByLease() {
        String date = console.promptForString("Enter the Date: ");
        String name = console.promptForString("Enter your name: ");
        String email = console.promptForString("Enter your email: ");
        System.out.println("Enter vehicle information");
        int vin = console.promptForInt("Enter VIN: ");


        ArrayList<Vehicle> vinNumber = d.getVehicleByVinNumber(vin);
        if (vinNumber.isEmpty()) {
            System.out.println(ColorCodes.RED + "No Vehicles with this Vin Number..." + ColorCodes.RESET);
        }

        for (Vehicle vehicle : vinNumber) {
            LeaseContract leaseContract = new LeaseContract(date, name, email, vehicle, vehicle.getPrice(), vehicle.getPrice());
            c.add(leaseContract);
            displayLease(c);
            com.pluralsight.ContractFileManager.saveContracts(leaseContract);
        }


    }


    private void processVehicleBySale() {
        String date = console.promptForString("Enter the Date: ");
        String name = console.promptForString("Enter your name: ");
        String email = console.promptForString("Enter your email: ");
        System.out.println("Enter vehicle information: ");
        int vin = console.promptForInt("Enter VIN: ");

        ArrayList<Vehicle> vinNumber = d.getVehicleByVinNumber(vin);

        if (vinNumber.isEmpty()) {
            System.out.println(ColorCodes.RED + "No Vehicles with this Vin Number..." + ColorCodes.RESET);
        }

        int finance = console.promptForInt("Do you want finance? Yes = 1. No = 0: ");
        boolean financeSelected = finance == 1;


        for (Vehicle vehicle : vinNumber) {
            SalesContract salesContract = new SalesContract(date, name, email, vehicle, financeSelected, vehicle.getPrice());
            c.add(salesContract);
            displaySales(c);
            com.pluralsight.ContractFileManager.saveContracts(salesContract);
            d.removeVehicle(vehicle);
            DealershipFileManager.saveDealership(d);

        }
    }
}