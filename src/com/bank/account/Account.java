package com.bank.account;

import com.bank.transaction.Transaction;

import java.util.ArrayList;
import java.util.Random;

public class Account {
    private double balance;
    private String accountNumber;
    private AccountType accountType;
    private ArrayList<Transaction> transactions;


    public Account(AccountType accountType) {
        this.accountNumber = generateAccountNumber();
        this.accountType = accountType;
    }

    public double getAccountBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    private String generateAccountNumber(){
        Random rand = new Random();
        long limit = (long)(rand.nextDouble()*10000000000L);
        return String.valueOf(limit);
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void deposit(String accountNumber, int amount) {
        final double MINIMUM_AMOUNT = 0.0;
        if(checkAccountNumber(accountNumber)){
            if(amount > MINIMUM_AMOUNT){
                balance += amount;
            }
        }
    }

    private boolean checkAccountNumber(String accountNumber) {
        return this.accountNumber.equals(accountNumber);
    }

    public void withdrawal(String accountNumber, int amount) {
        if(checkAccountNumber(accountNumber)){
            if(amount <= balance){
                balance -= amount;
            }
        }
    }

}
