package com.notification;

import com.Account;
import com.model.Transaction;

public class SmsAlert extends Alert{
    private String alertTitle;
    private String amount;
    private String amountInWords;
    private String accountNumber;
    private  String transactionType;
    private String actorName;
    private String availableBalance;
    private String transactionId;
    private String transactionDate;

    public SmsAlert(Account account, Transaction transaction) {
        super();
        this.alertTitle = transactionType + " Notification";
        this.amountInWords = transaction.getTransactionAmount().toString();
        this.accountNumber = account.getAccountNumber();
        this.transactionType = transactionType.toString();
        this.actorName = account.getAccountName();
        this.availableBalance = account.calculateAccountBalance().toString();
        this.transactionId = transaction.getTransactionId();
        this.transactionDate = transaction.getTransactionDate().toString();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(alertTitle.toUpperCase()).append("\n");
        stringBuilder.append(transactionId).append("\n");
        stringBuilder.append(transactionDate).append("\n");
        stringBuilder.append(accountNumber).append("\n");
        stringBuilder.append(amount).append("\n");
        stringBuilder.append(actorName).append("\n");
        stringBuilder.append(transactionType).append("\n");
        stringBuilder.append(availableBalance).append("\n");

        return stringBuilder.toString();
    }
}
