package com.model;

import java.math.BigDecimal;
import java.util.Optional;

public class TransferRequest {
    private final int senderPin;
    private final String senderAccountNumber;
    private final String recipientAccountNumber;
    private final BigDecimal accountToTransfer;
    private Bank senderBank;
    private String recipientBank;

    public TransferRequest(BigDecimal amountToTransfer, String senderAccountNumber, String recipientAccountNumber, int senderPin) {
        this.senderPin = senderPin;
        this.accountToTransfer = amountToTransfer;
        this.senderAccountNumber = senderAccountNumber;
        this.recipientAccountNumber = recipientAccountNumber;
    }

    public BigDecimal getAmountToTransfer() {
        return BigDecimal.ZERO;
    }

    public String getRecipientAccountNumber() {
        return null;
    }

    public String getSenderAccountNumber() {
        return null;
    }

    public int getSenderAccountPin() {
        return 0;
    }

    public Bank getSenderBank() {
        return senderBank;
    }

    public void setSenderBank(Bank senderBank) {
        this.senderBank = senderBank;
    }

    public String getRecipientBank() {
        return recipientBank;
    }

    public void setRecipientBank(String recipientBank) {
        this.recipientBank = recipientBank;
    }
}
