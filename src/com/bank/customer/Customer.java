package com.bank.customer;


import com.bank.account.Account;
import com.bank.account.AccountType;
import com.bank.date.DateAndTime;

import java.time.LocalDate;

public class Customer {
    private String firstName;
    private String lastName;
    private String occupation;
    private String address;
    private String phoneNumber;
    private String gender;
    private int yearOfBirth;
    private int monthOfBirth;
    private int dayOfBirth;
    private final Account account;
    private DateAndTime dateOfBirth;

    public Customer(String firstName, String lastName, String occupation, String address, String phoneNumber, String gender, String accountType, int yearOfBirth, int monthOfBirth, int dayOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.occupation = occupation;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        dateOfBirth = new DateAndTime(yearOfBirth, monthOfBirth, dayOfBirth);
        if(accountType.equals("savings"))
            this.account = new Account(AccountType.SAVINGS);
        else
            this.account = new Account(AccountType.CURRENT);
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
        return String.format("First name: %s%nLast name: %s%nDate of birth: %s%nAge: %s%nOccupation:%s%nAddress: %s%nPhone number: %s%nGender: %s%nAccount number: %s%nAccount balance: %s%nAccount type: %s BANK",
                firstName, lastName, dateOfBirth.dateOfBirthToString(), calculateAge(), occupation,address, phoneNumber, gender, account.getAccountNumber(), account.getAccountBalance(), account.getAccountType());
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public int getMonthOfBirth() {
        return monthOfBirth;
    }

    public int getDayOfBirth() {
        return dayOfBirth;
    }

    private int calculateAge(){
        int currentYear = LocalDate.now().getYear();
        System.out.println(getYearOfBirth());
        return (currentYear - getYearOfBirth());
    }


}
