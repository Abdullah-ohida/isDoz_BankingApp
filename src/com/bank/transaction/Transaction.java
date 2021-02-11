package com.bank.transaction;

import java.time.LocalDate;

public class Transaction {
    private String accountNumber;
    private int transactionAmount;
    private TransactionType transactionType;
    private LocalDate localDate;

    public Transaction(String accountNumber, int amount, TransactionType transactionType) {
        this.accountNumber = accountNumber;
        transactionAmount = amount;
        this.transactionType = transactionType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getTransactionAmount() {
        return transactionAmount;
    }

}
