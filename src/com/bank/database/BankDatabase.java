package com.bank.database;

import com.bank.customer.Customer;

import java.util.ArrayList;

public class BankDatabase {
    private ArrayList<Customer> customers;

    public BankDatabase() {
        customers = new ArrayList<>();
    }

    public int getDatabaseLength() {
        return customers.size();
    }


    public boolean addCustomerToDatabases(Customer newCustomer) {
        boolean customerExist = true;
        if(checkIfCustomerExist(findCustomer(newCustomer.getAccount().getAccountNumber()))){
            customerExist = false;
        }else {
            customers.add(newCustomer);
        }
       return customerExist;
    }

    private Customer findCustomer(String accountNumber){
        for(Customer customer : customers){
            if(customer.getAccount().getAccountNumber().equals(accountNumber)){
                return customer;
            }
        }
        return null;
    }


    public Customer printCustomerDetails(String accountNumber) {
        Customer customer = findCustomer(accountNumber);
        if(checkIfCustomerExist(customer)) {
            System.out.println("Here is your details. \n===================================");
            System.out.println(customer.displayUser());
        }
        return customer;
    }

    public Customer deleteCustomerFromDatabase(String accountNumber) {
        Customer customer = findCustomer(accountNumber);
        customers.remove(customer);
        return customer;
    }

    private boolean checkIfCustomerExist(Customer customer) {
        return customer != null;
    }

    public Customer updateCustomerDetails(String accountNumber, Customer updateCustomer) {
        Customer customer = findCustomer(accountNumber);
        if(checkIfCustomerExist(customer)){
            int position = findCustomerPosition(customer.getAccount().getAccountNumber());
            customers.set(position, updateCustomer);
        }
      return customer;
    }

    private int findCustomerPosition(String accountNumber) {
        int position = -1;
        for (int count = 0; count < customers.size(); count++){
            String checkedAccountNumber = customers.get(count).getAccount().getAccountNumber();
            if(checkedAccountNumber.equals(accountNumber)){
                position = count;
            }
        }
        return position;
    }
}
