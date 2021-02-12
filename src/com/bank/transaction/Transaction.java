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
        localDate = LocalDate.now();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getTransactionAmount() {
        return transactionAmount;
    }
    
    public String showAlert(){
        return String.format("Account number: %s%nAccount type: %s%nAmount transact: Date: %s", accountNumber, transactionType, transactionAmount, localDate.getDayOfWeek() + "/" + localDate.getMonth() + "/" + localDate.getYear());
    }

}
