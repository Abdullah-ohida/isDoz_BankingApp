package com.bank.main;

import com.bank.customer.Customer;
import com.bank.database.BankDatabase;

import java.util.Scanner;

public class Main {
 private static BankDatabase database = new BankDatabase("Union Bank");

    public static void main(String[] args) {

        Scanner getUserInput = new Scanner(System.in);
        int options ;
        displayServiceOptions();
        options = getUserInput.nextInt();



        while (options != 0){
            switchMethod(options);
            displayServiceOptions();
            options = getUserInput.nextInt();

            if (options < 0 || options > 6){
                System.out.println("Your option should be within 1 - 6\n");
            }
        }
    }

    public static void entryPoint(int options){
        Scanner userInput = new Scanner(System.in);
        System.out.print("Press 9 to see our service options: ");
        options = userInput.nextInt();
    }

    public static void displayServiceOptions(){
        System.out.print("Our Services: \n");
        System.out.println("\t\tPress 1 to create account with us");
        System.out.println("\t\tpress 2 to view your your balance");
        System.out.println("\t\tpress 3 to view your profile");
        System.out.println("\t\tpress 4 to delete and close your account");
        System.out.println("\t\tpress 5 to ask for a loan");
        System.out.println("\t\tPress 0 to quit\n");

        System.out.print("Enter your option here: ");
    }

    public static void switchMethod(int options){
        switch (options){
            case 1 -> createAccountMethod();
            case 2 -> makeTransactions();
            case 3 -> viewProfile();
            case 4 -> deleteAccount();
            case 5 -> getLoan();
            case 6 -> updateAccountInformation();
        }

    }

    private static void createAccountMethod() {
        Scanner userInput = new Scanner(System.in);

        System.out.print("Enter your first name: ");
        String firstName = userInput.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = userInput.nextLine();

        System.out.print("Enter your date of birth dd/mm/yyyy: ");
        String dateOfBirth = userInput.nextLine();

        System.out.print("Enter your occupation: ");
        String occupation = userInput.nextLine();

        System.out.print("Enter your address: ");
        String address = userInput.nextLine();

        System.out.print("Enter phone number: ");
        String phoneNumber = userInput.nextLine();

        System.out.print("Enter your gender: ");
        String gender = userInput.nextLine();

        Customer customer = new Customer(firstName, lastName, dateOfBirth, occupation, address, phoneNumber, gender);
        database.addCustomerToDatabases(customer);
        System.out.println("Account created successfully\n");
    }

    private static void makeTransactions(){
        Scanner userInput = new Scanner(System.in);
    }

    private static void viewProfile() {
        Scanner userInput = new Scanner(System.in);

        System.out.print("Enter your first name to view profile: ");
        String viewProfile = userInput.nextLine();
        database.printCustomerDetails(viewProfile);
    }

    private static void deleteAccount() {
        Scanner userInput = new Scanner(System.in);
        System.out.print("Do you want to delete your account (Y for delete/N for exit): ");
        String wantToDelete = userInput.nextLine().toLowerCase();

        if (wantToDelete.equals("y")){
            System.out.print("Enter your first name to delete your account: ");
            String deleteAccount = userInput.nextLine();
            database.deleteCustomerFromDatabase(deleteAccount);
            System.out.println("Account deleted successfully\n");

        }else
            displayServiceOptions();
    }

    private static void getLoan() {
    }

    private static void updateAccountInformation() {
    }
}
