package com;

import com.database.Storable;
import com.exception.InvalidPinException;
import com.exception.WithdrawFailedException;
import com.model.AccountType;
import com.model.Customer;
import com.model.Transaction;
import com.model.TransactionType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Account implements Storable {
    private final String accountNumber;
    private String accountName;
    private final List<Transaction> successfulTransactions;
    private final AccountType accountType;
    private int pin;
    private final String customerBvn;
    private final String bankName;

    public Account(Customer customer, String accountNumber, String bankName, AccountType accountType) {
        this.accountNumber = accountNumber;
        this.accountName = customer.getFirstName() + " " + customer.getLastName();
        successfulTransactions = new ArrayList<>();
        this.accountType = accountType;
        this.bankName = bankName;
        customerBvn = customer.getBvn();
    }

    public String getAccountName() {
        return accountName;
    }

    public int getPin() {
        return pin;
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

    public AccountType getAccountType() {
        return accountType;
    }

    public BigDecimal calculateAccountBalance() {
        BigDecimal customerCurrentAccountBalance = new BigDecimal(0);
        for(Transaction transaction : successfulTransactions){
            if(transaction.getTransactionType() == TransactionType.DEBIT  || transaction.getTransactionType() == TransactionType.TRANSFER_OUT) {
                customerCurrentAccountBalance = customerCurrentAccountBalance.subtract(transaction.getTransactionAmount());
            }
            if(transaction.getTransactionType() == TransactionType.CREDIT || transaction.getTransactionType() == TransactionType.TRANSFER_IN) {
                customerCurrentAccountBalance = customerCurrentAccountBalance.add(transaction.getTransactionAmount());
            }
        }
        return customerCurrentAccountBalance;
    }

    public void verifyLegibilityForWithdraw(BigDecimal amountToWithdraw, int accountPin) throws WithdrawFailedException {
        verifyLegibilityForWithdrawalWith(amountToWithdraw, accountPin);
    }

    private void verifyLegibilityForWithdrawalWith(BigDecimal amountToWithdraw, int accountPin) throws WithdrawFailedException {
        boolean pinIsNotCorrect = accountPin != getPin();
        if(pinIsNotCorrect) throw new WithdrawFailedException("Incorrect Pin");
        boolean funIsInsufficient = amountToWithdraw.compareTo(calculateAccountBalance()) > 0;
        if (funIsInsufficient) throw new WithdrawFailedException("Insufficient Funds");
    }

    @Override
    public String toString() {
        String accountProfile = "";
        accountProfile += "Account Name: " + accountName + "\n";
        accountProfile += "Account Number: " + accountNumber + "\n";
        accountProfile += "Bank Name: " + accountType.toString() + "\n";
        accountProfile += "Customer BVN: " + customerBvn + "\n";

        return accountProfile;
    }
}
