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
        if(findCustomer(newCustomer.getFirstName())){
            System.out.println("Customer Already Exit On Our PlatForm.");
            return false;
        }
        customers.add(newCustomer);
        return true;
    }

    private boolean findCustomer(String customerName){
        for(Customer customer : customers){
            if(customer.getFirstName().equals(customerName)){
                return true;
            }
        }
        return false;
    }


}
