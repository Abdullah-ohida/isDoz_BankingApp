package com.bank.transaction;

import com.bank.date.DateAndTime;

public class Transaction {
    private String accountNumber;
    private double transactionAmount;
    private TransactionType transactionType;
    private DateAndTime dateAndTime;

    public Transaction(String accountNumber, double amount, TransactionType transactionType) {
        this.accountNumber = accountNumber;
        transactionAmount = amount;
        this.transactionType = transactionType;
        dateAndTime = new DateAndTime();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }
    
    public String showAlert(){
        return String.format("Account number: %s%nTransaction type: %s%nTransaction amount : %s%nTime of transaction: %s%nDate of transaction: %s%n===========================================\n",
                accountNumber, transactionType, transactionAmount, dateAndTime.timeToStringFormat(), dateAndTime.dateToStringFormat());
    }

    public DateAndTime getDate() {
        return dateAndTime;
    }
}
