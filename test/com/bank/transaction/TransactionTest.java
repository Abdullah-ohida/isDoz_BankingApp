package com.bank.transaction;

import com.bank.account.Account;
import com.bank.account.AccountType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {
    Transaction transaction;
    Account account;

    @BeforeEach
    void setUp() {

        account = new Account(AccountType.CURRENT);
    }

    @AfterEach
    void tearDown() {
        transaction = null;
        account = null;
    }

    @Test
    void transaction_hasTransactionType(){
        String accountNumber = account.getAccountNumber();
        int amount = 6000;

        boolean isSuccessful = account.performTransaction(TransactionType.DEPOSIT, accountNumber, amount);
        assertTrue(isSuccessful);
        transaction = new Transaction(accountNumber, amount, account.getTransactionType());
        assertEquals(TransactionType.DEPOSIT, account.getTransactionType());
    }

    @Test
    void transaction_hasTransactionAmount(){
        String accountNumber = account.getAccountNumber();
        int salary = 6000;

        boolean isSuccessful = account.performTransaction(TransactionType.DEPOSIT, accountNumber, salary);
        assertTrue(isSuccessful);
        assertEquals(6000, account.getAccountBalance());


        transaction = new Transaction(accountNumber, 2000, account.getTransactionType());
        assertEquals(2000, transaction.getTransactionAmount());
    }

    @Test
    void transaction_canShowAccountNumberOfTransactionBeenMade(){
        String accountNumber = account.getAccountNumber();
        int amount = 700;

        boolean isSuccessful = account.performTransaction(TransactionType.DEPOSIT, accountNumber, amount);
        assertTrue(isSuccessful);
        assertEquals(700, account.getAccountBalance());

        transaction = new Transaction(accountNumber, 700,account.getTransactionType());
        assertEquals(accountNumber, transaction.getAccountNumber());
    }

    @Test
    void transaction_hasTimeTransactionBeenProcessed(){
        String accountNumber = account.getAccountNumber();
        int amount = 700;

        boolean isSuccessful = account.performTransaction(TransactionType.DEPOSIT, accountNumber, amount);
        transaction = new Transaction(accountNumber, 700,account.getTransactionType());

        assertTrue(isSuccessful);
        assertNotNull(transaction.getDate().timeToStringFormat());
    }

    @Test
    void transaction_hasDateTransactionBeenProcessed(){
        String accountNumber = account.getAccountNumber();
        int amount = 700;

        boolean isSuccessful = account.performTransaction(TransactionType.DEPOSIT, accountNumber, amount);
        transaction = new Transaction(accountNumber, 700,account.getTransactionType());

        assertNotNull(transaction.getDate().dateToStringFormat());
        System.out.println(transaction.getDate().dateToStringFormat());
        assertTrue(isSuccessful);
    }


    @Test
    void transaction_canShowMessage(){
        String accountNumber = account.getAccountNumber();
        int salary = 6000;

        boolean isSuccessful = account.performTransaction(TransactionType.DEPOSIT, accountNumber, salary);
        assertTrue(isSuccessful);
        assertEquals(6000, account.getAccountBalance());


        transaction = new Transaction(accountNumber, 2000, account.getTransactionType());

        System.out.println(transaction.showAlert());
        assertNotNull(transaction.showAlert());
    }

}