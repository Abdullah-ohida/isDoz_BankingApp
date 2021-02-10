package com.bank.customer;



public class Customer {

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String occupation;
    private String address;
    private String phoneNumber;
    private String gender;

    public Customer(String firstName, String lastName, String dateOfBirth, String occupation, String address, String phoneNumber, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.occupation = occupation;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
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

    public String  displayUser(){
        return String.format("First name: %s%nLast name: %s%nDate of birth: %s%nOccupation:%s%nAddress: %s%nPhone number: %s%nGender: %s",
                firstName, lastName, dateOfBirth, occupation,address, phoneNumber, gender);
    }


}
