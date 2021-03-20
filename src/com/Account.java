package com;

import com.database.Storable;
import com.model.AccountType;
import com.model.Customer;
import com.model.Transaction;
import com.model.TransactionType;

import java.util.ArrayList;
import java.util.List;

public class Account implements Storable {
    private final String accountNumber;
    private String accountName;
    private final List<Transaction> successfulTransactions;
    private int pin;

    public Account(Customer customer, String accountNumber, String bankName, AccountType accountType) {
        this.accountNumber = accountNumber;
        this.accountName = customer.getFirstName() + " " + customer.getLastName();
        successfulTransactions = new ArrayList<>();
    }

    public String getAccountName() {
        return accountName;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public void updatePin(int oldPin, int newPin) {
        if (oldPin == pin){
            setPin(newPin);
        }
    }

    public List<Transaction> getTransactions() {
        return successfulTransactions;
    }
}
