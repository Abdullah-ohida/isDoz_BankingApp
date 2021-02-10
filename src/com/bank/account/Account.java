package com.bank.account;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Account {
    private int balance;
    private String accountNumber;
    private ArrayList<Double> transaction;


    public Account() {
        this.transaction = new ArrayList<>();
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    private String GenerateAccountNumber(){
        Random rand = new Random();
        long limit = (long)(rand.nextDouble()*10000000000L);
        return String.valueOf(limit);
    }


}
