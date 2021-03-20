package com.model;

import com.Account;
import com.database.CentralDB;
import com.database.CentralDBImp;

import java.util.List;
import java.util.Map;

public class Customer {
    private String firstName;
    private String lastName;
    private String address;
    private CentralDB<Account> accounts;
    private String bvn;

    public Customer(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.accounts = new CentralDBImp<>();
    }

    public void addAccount(Account account) {
        accounts.save(account);
    }

    public String getBvn() {
        return bvn;
    }

    public void setBvn(String bvn) {
        this.bvn = bvn;
    }

    public List<Account> getMyAccount(){
        return accounts.findAll();
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
}
