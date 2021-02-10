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

            if (options < 1 || options > 7){
                System.out.println("Your option should be within 1 - 6\n");
            }
        }
    }

    public static void displayServiceOptions(){
        System.out.println("Press 1 to create account with us");
        //if the account number exists it shout say account exists
        System.out.println("press 2 to view your your balance");
        System.out.println("press 3 to view your profile");
        System.out.println("press 4 to close your account");
        System.out.println("press 5 to ask for a loan");
        System.out.println("Press 0 to quit");
        System.out.println();
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

        System.out.println("Enter your gender: ");
        String gender = userInput.nextLine();

        Customer customer = new Customer(firstName, lastName, dateOfBirth, occupation, address, phoneNumber, gender);
        database.addCustomerToDatabases(customer);
        System.out.println("Account created successfully");
    }

    private static void makeTransactions(){
        Scanner userInput = new Scanner(System.in);
    }

    private static void viewProfile() {
        Scanner userInput = new Scanner(System.in);

        System.out.print("Enter your first name to view profile");
        String viewProfile = userInput.nextLine();
        database.printCustomerDetails(viewProfile);
    }

    private static void deleteAccount() {
        Scanner userInput = new Scanner(System.in);

        System.out.print("Enter your first name to delete your account: ");
        String deleteAccount = userInput.nextLine();
        database.deleteCustomerFromDatabase(deleteAccount);
        System.out.println("Account deleted successfully");
    }

    private static void getLoan() {
    }

    private static void updateAccountInformation() {
    }
}
