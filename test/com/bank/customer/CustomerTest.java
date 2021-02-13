package com.bank.customer;

import com.bank.transaction.TransactionType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    Customer newCustomer;
    Customer newCustomer1;

    @BeforeEach
    void setUp() {
        newCustomer = new Customer("DonDozie", "MoneyMan",
                "Software Engineer", "Sabo", "12345677", "Male", "savings", 1997, 8, 7);

        newCustomer1 = new Customer("Ismail", "MoneyMan",
                "Software Engineer", "Sabo", "12345677", "Male", "savings", 2001, 8, 12);
    }

    @AfterEach
    void tearDown() {
        newCustomer = null;
    }

    @Test
    void customer_hasAFirstName(){
        assertNotNull(newCustomer.getFirstName());
    }

    @Test
    void customer_canSetFirstName(){
        newCustomer.setFirstName("DonDozie");
        assertEquals("DonDozie", newCustomer.getFirstName());
    }

    @Test
    void customer_hasALastName(){
        assertNotNull(newCustomer.getLastName());
    }

    @Test
    void customer_canSetLastName(){
        newCustomer.setLastName("Billy");
        assertEquals("Billy", newCustomer.getLastName());
    }

    @Test
    void customer_hasOccupation(){
        newCustomer.setOccupation("trader");
        assertEquals("trader", newCustomer.getOccupation());
    }

    @Test
    void customer_hasAddress(){
        newCustomer.setAddress("Sabo");
        assertEquals("Sabo", newCustomer.getAddress());
    }

    @Test
    void customer_hasPhoneNumber(){
        newCustomer.setPhoneNumber("12345678");
        assertEquals("12345678", newCustomer.getPhoneNumber());
    }

    @Test
    void customer_hasGender(){
       newCustomer.setGender("Male");
        System.out.println(toString());
       assertEquals("Male", newCustomer.getGender());
    }

    @Test
    void customer_hasAccountNumber(){
        assertNotNull(newCustomer.getAccount().getAccountNumber());
    }

    @Test
    void customer_canDepositToAccount(){
        double amount = 9000;
        String accountNumber = newCustomer.getAccount().getAccountNumber();
        newCustomer.getAccount().performTransaction(TransactionType.DEPOSIT, accountNumber, amount);
        assertEquals(9000, newCustomer.getAccount().getAccountBalance());
    }

    @Test
    void customer_canWithdrawFromAccount(){
        double amount = 9000;
        String accountNumber = newCustomer.getAccount().getAccountNumber();
        newCustomer.getAccount().performTransaction(TransactionType.DEPOSIT, accountNumber, amount);

        newCustomer.getAccount().performTransaction(TransactionType.WITHDRAWAL, accountNumber, 900);
        assertEquals(8100, newCustomer.getAccount().getAccountBalance());
    }

    @Test
    void customer_canShowTransactionOfACustomer(){
        double amount = 9000;
        String accountNumber = newCustomer.getAccount().getAccountNumber();
        newCustomer.getAccount().performTransaction(TransactionType.DEPOSIT, accountNumber, amount);
        newCustomer.getAccount().performTransaction(TransactionType.WITHDRAWAL, accountNumber, 900);

        newCustomer1.getAccount().performTransaction(TransactionType.DEPOSIT, newCustomer1.getAccount().getAccountNumber(), 300);

        boolean exist  = newCustomer.getAccount().showAllCustomerTransaction(accountNumber);
        assertTrue(exist);

        exist  = newCustomer1.getAccount().showAllCustomerTransaction(newCustomer1.getAccount().getAccountNumber());
        assertTrue(exist);

    }

}