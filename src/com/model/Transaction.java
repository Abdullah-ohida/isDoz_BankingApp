package com.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private final String transactionId;
    private final TransactionType transactionType;
    private final LocalDateTime transactionDate;
    private final BigDecimal transactionAmount;
    private final String actorName;

    public Transaction(String transactionId, TransactionType transactionType, BigDecimal transactionAmount, String actorName) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.transactionDate = LocalDateTime.now();
        this.transactionAmount = transactionAmount;
        this.actorName = actorName;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public String getActorName() {
        return actorName;
    }
}
