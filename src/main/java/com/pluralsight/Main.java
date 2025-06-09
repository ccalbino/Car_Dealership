package com.pluralsight;


public class Main {
    public static void main(String[] args) {


        uiManagement();


    }

    private static void uiManagement(){
        Console console = new Console();
        String password;

        System.out.println("Welcome to the Car Dealership");
        System.out.println("If you are an Admin, please sign in with your password");
        System.out.println("If you are a User, Just ignore the password and press Enter");
        System.out.println("If you want to quit please Enter the 0 key");
        do {
            System.out.println("If you are a User, Just ignore the password and press Enter");


            password = console.promptForString("Enter your password: ");

            if(password.equalsIgnoreCase("Admin")){
                System.out.println("Correct Password... Moving to Admin UI");

                AdminUserInterface UI = new AdminUserInterface();

                UI.displayMenu();



            } else if(password.isEmpty()){
                System.out.println("Moving to User Interface");

                UserInterface UI = new UserInterface();

                UI.displayMenu();
                break;
            } else if (password.equalsIgnoreCase("0")) {
                System.out.println("Quitting application...");
                break;

            } else {
                System.out.println("Wrong Password...");

            }

        } while(!password.equalsIgnoreCase("AdminPassword"));


    }

}