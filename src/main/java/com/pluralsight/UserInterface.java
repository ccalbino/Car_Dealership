package com.pluralsight;


import java.util.ArrayList;


public class UserInterface {


    private Dealership d;
    private ArrayList<Contract> c = new ArrayList<>();
    private final Console console = new Console();




    private void init() {
        d = DealershipFileManager.getDealership();
        c = ContractFileManager.getContracts();



    }

    public void displayMenu() {
        init();

        display();
    }


    private void displayVehicles(ArrayList<Vehicle> vehicles) {

        for (Vehicle v : vehicles)
            System.out.println(v.toString());

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


    private void display() {

        int input;
        String homeScreenPrompt = """
                What do you want to do:\s
                 1 - Find vehicles within a price range\s
                 2 - Find vehicles by make / model\s
                 3 -  Find vehicles by year range
                 4 -  Find vehicles by color
                 5 -  Find vehicles by mileage range
                 6 -  Find vehicles by type (car, truck, SUV, van)
                 7 - List ALL vehicles
                 8 - Add a vehicle
                 9 - Remove a vehicle
                 10- Create A Sale
                 11- Create A Lease
                 0 - Quit
                Enter your command(number 1-11):\s""";


        do {
            input = console.promptForInt(homeScreenPrompt);
            switch (input) {
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
                    processGetByAllVehiclesRequest();
                    break;
                case 8:
                    processAddVehicleRequest();
                    break;
                case 9:
                    startProcessToRemoveVehicle();
                    break;
                case 10:
                    processVehicleBySale();
                    break;
                case 11:
                    processVehicleByLease();
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


    private void processGetByPriceRequest() {

        double min = console.promptForDouble("Input the minimum price: ");
        double max = console.promptForDouble("Input the maximum price: ");

        ArrayList<Vehicle> priceResult = d.getVehicleByPrice(min, max);


        if (priceResult.isEmpty()) {
            System.out.println( ColorCodes.RED + "No Vehicles found within price range" + ColorCodes.RESET);
        } else {
            System.out.println("These are the vehicles found within" + " " + min + " and " + max);
            System.out.println(Vehicle.getFormattedHeader());
            displayVehicles(priceResult);

        }
    }


    private void processGetByMakeModelRequest() {

        String make = console.promptForString("Input Make you want to search for: ");
        String model = console.promptForString("Input Model you want to search for: ");

        ArrayList<Vehicle> makeModelResult = d.getVehicleByMakeModel(make, model);

        if (makeModelResult.isEmpty()) {
            System.out.println( ColorCodes.RED + "No Vehicles found within " + make + " " + model + ColorCodes.RESET);
        } else {
            System.out.println("These are the Vehicles found within " + make + " " + model);
            System.out.println(Vehicle.getFormattedHeader());
            displayVehicles(makeModelResult);
        }

    }


    private void processGetByYearRequest() {
        int minYear = console.promptForInt("Enter in the minimum year: ");
        int maxYear = console.promptForInt("Enter in the maximum year: ");
        ArrayList<Vehicle> yearResult = d.getVehicleByYear(minYear, maxYear);


        if (yearResult.isEmpty()) {
            System.out.println(ColorCodes.RED + "No Vehicle found for " + minYear + " " + maxYear + ColorCodes.RESET);

        } else {
            System.out.println("These Vehicles are inside of this year: " + yearResult);
            System.out.println(Vehicle.getFormattedHeader());


            displayVehicles(yearResult);
        }


    }


    private void processGetByColorRequest() {

        String color = console.promptForString("Enter in a color: ");
        ArrayList<Vehicle> colorResult = d.getVehicleByColor(color);


        if (colorResult.isEmpty()) {
            System.out.println(ColorCodes.RED + "No Vehicles of this color: " + color + ColorCodes.RESET);
        } else {
            System.out.println("These are the Vehicles of this color: " + color);
            System.out.println(Vehicle.getFormattedHeader());
            displayVehicles(colorResult);
        }

    }


    private void processGetByMileageRequest() {

        int startingMileage = console.promptForInt("Enter in the starting Mileage you want to search: ");
        int endingMileage = console.promptForInt("Enter in the ending Mileage you want to search: ");

        ArrayList<Vehicle> mileageResult = d.getVehicleByMileage(startingMileage, endingMileage);

        if (mileageResult.isEmpty()) {
            System.out.println( ColorCodes.RED + "No Vehicles have a mileage between these two numbers " + startingMileage + " " + endingMileage + ColorCodes.RESET);
        } else {
            System.out.println("These Vehicles have a mileage between " + startingMileage + " " + endingMileage);
            System.out.println(Vehicle.getFormattedHeader());
            displayVehicles(mileageResult);
        }

    }


    private void processGetByVehicleTypeRequest() {
        String type = console.promptForString("Enter Vehicle Type ");

        ArrayList<Vehicle> typeResult = d.getVehicleByType(type);

        if (typeResult.isEmpty()) {
            System.out.println( ColorCodes.RED + "There are no Vehicles with the type of " + type + ColorCodes.RESET);
        } else {
            System.out.println("These are the Vehicles with the type of " + type);
            System.out.println(Vehicle.getFormattedHeader());

            displayVehicles(typeResult);
        }

    }


    private void processGetByAllVehiclesRequest() {
        System.out.println("Here are all the vehicles");
        System.out.println(Vehicle.getFormattedHeader());

        displayVehicles(d.getAllVehicles());

    }


    private void processAddVehicleRequest() {
        System.out.println("Enter vehicle information");

        int vin = console.promptForInt("Enter VIN: ");
        int year = console.promptForInt("Enter Year: ");
        String make = console.promptForString("Enter Make: ");
        String model = console.promptForString("Enter Model: ");
        String type = console.promptForString("Vehicle Type: ");
        String color = console.promptForString("Enter Color: ");
        int odometer = console.promptForInt("Enter Distance ");
        double price = console.promptForDouble("Enter Price: ");

        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
        d.addVehicle(vehicle);
        DealershipFileManager.saveDealership(d);

    }


    private void startProcessToRemoveVehicle() {
        int choice;
        do {


            choice = console.promptForInt("Do you want to check the Vehicle list? (1 or 2 ):   ");
            switch (choice) {
                case 1:
                    displayVehicles(d.getAllVehicles());
                    break;
                case 2:
                    processRemoveVehicleRequest();
                    break;
            }


        } while (choice != 2);
    }


    private void processRemoveVehicleRequest() {

        System.out.println("Which Vehicle do you want to Remove?: ");
        int vin = console.promptForInt("Enter VIN: ");

        ArrayList<Vehicle> choiceResult = d.getVehicleByVinNumber(vin);


        if (choiceResult.isEmpty()) {
            System.out.println( ColorCodes.RED + "No Vehicle...." + ColorCodes.RESET);

        } else {
            for (Vehicle vehicle : choiceResult) {
                d.removeVehicle(vehicle);
                System.out.println( ColorCodes.PURPLE +choiceResult + " Was Removed...." + ColorCodes.RESET);
            }
        }
        DealershipFileManager.saveDealership(d);

    }


    private void processVehicleByLease() {
        String date = console.promptForString("Enter the Date: ");
        String name = console.promptForString("Enter your name: ");
        String email = console.promptForString("Enter your email: ");
        System.out.println("Enter vehicle information");
        int vin = console.promptForInt("Enter VIN: ");


        ArrayList<Vehicle> vinNumber = d.getVehicleByVinNumber(vin);
        if(vinNumber.isEmpty()){
            System.out.println( ColorCodes.RED + "No Vehicles with this Vin Number..." + ColorCodes.RESET);
        }

        for (Vehicle vehicle : vinNumber) {
            LeaseContract leaseContract = new LeaseContract(date, name, email, vehicle, vehicle.getPrice(), vehicle.getPrice());
            c.add(leaseContract);
            displayLease(c);
            ContractFileManager.saveContracts(leaseContract);
        }
    }


    private void processVehicleBySale() {
        String date = console.promptForString("Enter the Date: ");
        String name = console.promptForString("Enter your name: ");
        String email = console.promptForString("Enter your email: ");
        System.out.println("Enter vehicle information: ");


        int vin = console.promptForInt("Enter VIN: ");
        ArrayList<Vehicle> vinNumber = d.getVehicleByVinNumber(vin);




        int finance = console.promptForInt("Do you want finance? Yes = 1. No = 0: ");
        boolean financeSelected = (finance == 1);


        if(vinNumber.isEmpty()){
            System.out.println( ColorCodes.RED + "No Vehicles with this Vin Number..." + ColorCodes.RESET);
        }

        for (Vehicle vehicle : vinNumber) {
            SalesContract salesContract = new SalesContract(date, name, email, vehicle, financeSelected, vehicle.getPrice());
            c.add(salesContract);
            d.removeVehicle(vehicle);
            displaySales(c);
            com.pluralsight.ContractFileManager.saveContracts(salesContract);
        }
    }

}