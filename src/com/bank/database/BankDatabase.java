package com.bank.database;

import com.bank.customer.Customer;

import java.util.ArrayList;

public class BankDatabase {
    private String name;
    private ArrayList<Customer> customers;

    public BankDatabase(String name) {
        this.name = name;
        customers = new ArrayList<>();
    }

    public int getDatabaseLength() {
        return customers.size();
    }


    public boolean addCustomerToDatabases(Customer newCustomer) {
        boolean customerExist = true;
        if(findCustomer(newCustomer.getFirstName()) != null){
            customerExist = false;
        }else {
            customers.add(newCustomer);
        }
       return customerExist;
    }

    private Customer findCustomer(String customerName){
        for(Customer customer : customers){
            if(customer.getFirstName().equals(customerName)){
                return customer;
            }
        }
        return null;
    }


    public Customer printCustomerDetails(String customerName) {
        Customer customer = findCustomer(customerName);
        if(customer != null) {
            System.out.println("Here is your details. \n===================================\n");
            System.out.println(customer.displayUser());
        }
        return customer;
    }

    public Customer deleteCustomerFromDatabase(String customerName) {
        Customer customer = findCustomer(customerName);
        if(customer != null)
            customers.remove(customer);
        return null;
    }

    public Customer updateCustomerDetails(String currentName, Customer updateCustomer) {
        Customer customer = findCustomer(currentName);
        if(customer != null){
            int position = findCustomerPosition(customer.getFirstName());
            customers.set(position, updateCustomer);
        }
      return customer;
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
