package com.pluralsight;

import java.util.ArrayList;

public class Dealership {

    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventoryList;



    public Dealership(String name, String address, String phone){
        this.name = name;
        this.address = address;
        this.phone = phone;

    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public ArrayList<Vehicle> getVehicleByPrice(double min , double max){
        ArrayList<Vehicle>endResult = new ArrayList<>();
        for(Vehicle v : inventoryList){
            if(v.getPrice() >= min && v.getPrice() <= max){
                endResult.add(v);
            }
        }
        return endResult;

    }
    public ArrayList<Vehicle> getVehicleByMakeModel(String make, String model){
        ArrayList<Vehicle>endResult = new ArrayList<>();
        for(Vehicle v : inventoryList){
            if(v.getMake().equalsIgnoreCase(make)){
                endResult.add(v);
            }
        }

        for (Vehicle v : inventoryList){
            if (v.getModel().equalsIgnoreCase(model)){
                endResult.add(v);
            }
        }

        return endResult;


    }
    public ArrayList<Vehicle> getVehicleByYear(int min , int max){
        ArrayList<Vehicle>endResult = new ArrayList<>();
        for ( Vehicle v : inventoryList){
            if(v.getYear() >= min && v.getYear() <= max ){
                endResult.add(v);
            }
        }
        return endResult;


    }
    public ArrayList<Vehicle> getVehicleByColor(String color){
        ArrayList<Vehicle>endResult = new ArrayList<>();
        for(Vehicle v : inventoryList){
            if(v.getColor().equalsIgnoreCase(color)){
                endResult.add(v);
            }
        }

        return endResult;
    }
    public ArrayList<Vehicle> getVehicleByMileage(int min, int max){
        ArrayList<Vehicle>endResult = new ArrayList<>();
        for(Vehicle v : inventoryList){
            if(v.getMileage() >= min && v.getMileage() <= max){
                endResult.add(v);
            }
        }


        return endResult;

    }
    public ArrayList<Vehicle> getVehicleByType(String type){
        ArrayList<Vehicle>endResult = new ArrayList<>();
        for(Vehicle v : inventoryList){
            if(v.getVehicleType().equalsIgnoreCase(type)){
                endResult.add(v);
            }
        }



        return endResult;
    }
    public ArrayList<Vehicle> getAllVehicles(){
        return inventoryList;
    }

    public void addVehicle(Vehicle vehicle){

        inventoryList.add(vehicle);



    }

    public void removeVehicle(Vehicle vehicle){

        inventoryList.remove(vehicle);
    }


    public String toStringDealership() {

        return  this.name + "|" +
                this.address + "|"+
                this.phone + "|";

    }



}



