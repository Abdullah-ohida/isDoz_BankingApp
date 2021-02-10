package com.bank.main;

import com.bank.customer.Customer;

import java.util.Scanner;

public class Main {

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
        System.out.println("press 3 to make a deposit");
        System.out.println("press 4 to withdraw");
        System.out.println("press 5 to ask for a loan");
        System.out.println("Press 0 to quit");
    }

    public static void switchMethod(int options){
        switch (options){
            case 1 -> createAccountMethod();
            case 2 -> makeTransactions();
            case 3 -> viewProfile();
            case 4 -> deleteAccount();
            case 5 -> getLoan();
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
    }

    private static void makeTransactions(){
        Scanner userInput = new Scanner(System.in);


    }

    private static void viewProfile() {
    }

    private static void deleteAccount() {
    }

    private static void getLoan() {
    }
}
