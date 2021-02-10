package com.bank.database;

import com.bank.customer.Customer;

import java.util.ArrayList;

public class BankDatabase {
    private String name;
    private ArrayList<Customer> customers;

    public BankDatabase(String name) {
        this.name = name;
        customers = new ArrayList<Customer>();
    }

    public int getDatabaseLength() {
        return customers.size();
    }

    public boolean addCustomerToDatabases(Customer newCustomer) {
        if(findCustomer(newCustomer.getFirstName()) != null){
            System.out.println("Customer Already Exit On Our PlatForm.");
            return false;
        }
        customers.add(newCustomer);
        return true;
    }

    private Customer findCustomer(String customerName){
        for(Customer customer : customers){
            if(customer.getFirstName().equals(customerName)){
                return customer;
            }
        }
        return null;
    }


    public void printCustomerDetails(String customerName) {
        Customer customer = findCustomer(customerName);
        System.out.println("Here is your details. \n===================================\n");
        assert customer != null;
        System.out.println(customer.displayUser());
    }
}
