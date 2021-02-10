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


    public boolean printCustomerDetails(String customerName) {
        Customer customer = findCustomer(customerName);
        if(customer != null) {
            System.out.println("Here is your details. \n===================================\n");
            System.out.println(customer.displayUser());
            return true;
        }
        return false;
    }

    public boolean deleteCustomerFromDatabase(String customerName) {
        Customer customer = findCustomer(customerName);
        if(customer != null){
            customers.remove(customer);
            return true;
        }
        return false;
    }

    public boolean updateCustomerDetails(String currentName, Customer updateCustomer) {
        Customer customer = findCustomer(currentName);
        if(customer != null){
            int position = findCustomerPosition(customer.getFirstName());
            customers.set(position, updateCustomer);
            return true;
        }
      return false;
    }

    private int findCustomerPosition(String customerName) {
        int position = -1;
        for (int count = 0; count < customers.size(); count++){
            String checkedName = customers.get(count).getFirstName();
            if(checkedName.equals(customerName)){
                position = count;
            }
        }
        return position;
    }
}
