package com.bank.account;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    Account firstAccount;
    Account secondAccount;

    @BeforeEach
    void setUp() {
        firstAccount = new Account(AccountType.SAVINGS);
        secondAccount = new Account(AccountType.SAVINGS);
    }

    @AfterEach
    void tearDown() {
        firstAccount = null;
        secondAccount = null;
    }

    @Test
    void account_generatedAreNotTheSame(){
        assertNotEquals(firstAccount.getAccountNumber(), secondAccount.getAccountNumber());
    }

    @Test
    void account_canGenerateAccountNumber(){
        assertNotNull(firstAccount.getAccountNumber());
    }

    @Test
    void account_TypeIsSavings(){
        assertEquals(AccountType.SAVINGS, firstAccount.getAccountType());
    }

    @Test
    void account_TypeIsCurrent(){
        Account newAccount = new Account(AccountType.CURRENT);
        assertEquals(AccountType.CURRENT, newAccount.getAccountType());
    }

    @Test
    void account_canDepositFromAccount(){
        int amount = 3000;
        String accountNumber = firstAccount.getAccountNumber();

        firstAccount.deposit(accountNumber, amount);
        assertEquals(amount, firstAccount.getAccountBalance());
    }

    @Test
    void account_canValidateIfAmountIsANegativeValue(){
        int amount = -743;
        String accountNumber = secondAccount.getAccountNumber();

        secondAccount.deposit(accountNumber, amount);
        assertEquals(0.0, secondAccount.getAccountBalance());
    }
    @Test
    void account_canWithdrawFromAccount(){
        int amount = 3000;
        String accountNumber = firstAccount.getAccountNumber();

        firstAccount.deposit(accountNumber, amount);

        firstAccount.withdrawal(accountNumber, amount);
        assertEquals(0.0, firstAccount.getAccountBalance());
    }

    @Test
    void account_canValidateIfAmountToWithdrawIsGreaterThanAccountBalance(){
        int amount = 3000;
        String accountNumber = firstAccount.getAccountNumber();

        firstAccount.deposit(accountNumber, amount);

        firstAccount.withdrawal(accountNumber, 5000);
        assertEquals(3000, firstAccount.getAccountBalance());
    }


}