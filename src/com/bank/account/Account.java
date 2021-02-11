package com.bank.account;

import com.bank.transaction.Transaction;
import com.bank.transaction.TransactionType;

import java.util.ArrayList;
import java.util.Random;

public class Account {
    private double balance;
    private String accountNumber;
    private AccountType accountType;
    private ArrayList<Transaction> transactions;
    private TransactionType transactionType;


    public Account(AccountType accountType) {
        this.accountNumber = generateAccountNumber();
        this.accountType = accountType;
    }

    public TransactionType getTransactionType() {
        return transactionType;
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

    public void setTransactionType(TransactionType transactionType, boolean isSuccessful) {
        if (isSuccessful){
            this.transactionType = transactionType;
        }

    }

    public boolean performTransaction(TransactionType transactionType, String accountNumber, int amount){
        this.transactionType = transactionType;
        boolean isSuccessful;
        if(transactionType == TransactionType.WITHDRAWAL){
           isSuccessful = withdrawal(accountNumber, amount);
           setTransactionType(transactionType, isSuccessful);
        }else {
            isSuccessful = deposit(accountNumber, amount);
        }
        return isSuccessful;
    }

    public boolean deposit(String accountNumber, int amount) {
        final double MINIMUM_AMOUNT = 0.0;
        boolean isValid = false;
        if(checkAccountNumber(accountNumber)){
            if(amount > MINIMUM_AMOUNT){
                balance += amount;
                isValid = true;
            }
        }
        return isValid;
    }

    private boolean checkAccountNumber(String accountNumber) {
        return this.accountNumber.equals(accountNumber);
    }

    boolean withdrawal(String accountNumber, int amount) {
        boolean isValid = false;
        if(checkAccountNumber(accountNumber)){
            if(amount <= balance){
                balance -= amount;
                isValid = true;
            }
        }
        return isValid;
    }
}
