package com.bank.customer;


import com.bank.account.Account;
<<<<<<< HEAD
=======
import com.bank.account.AccountType;

import java.util.ArrayList;
>>>>>>> local

import java.util.ArrayList;

public class Customer {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String occupation;
    private String address;
    private String phoneNumber;
    private String gender;
<<<<<<< HEAD
    private ArrayList<Account> accounts;
=======
    private Account account;
>>>>>>> local

    public Customer(String firstName, String lastName, String dateOfBirth, String occupation, String address, String phoneNumber, String gender, String accountType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.occupation = occupation;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
<<<<<<< HEAD
        accounts = new ArrayList<>();
=======
        if(accountType.equals("savings"))
            this.account = new Account(AccountType.SAVINGS);
        else
            this.account = new Account(AccountType.CURRENT);
>>>>>>> local
    }
    
    

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }


    public void setDateOfBirth(String dataOfBirth){
        this.dateOfBirth = dataOfBirth;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Account getAccount() {
        return account;
    }

    public String  displayUser(){
        return String.format("First name: %s%nLast name: %s%nDate of birth: %s%nOccupation:%s%nAddress: %s%nPhone number: %s%nGender: %s%n",
                firstName, lastName, dateOfBirth, occupation,address, phoneNumber, gender);
    }



}
