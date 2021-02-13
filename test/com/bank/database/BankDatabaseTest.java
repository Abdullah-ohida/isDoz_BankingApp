package com.bank.database;

import com.bank.customer.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankDatabaseTest {
    BankDatabase database;
    Customer newCustomer;
    Customer anotherCustomer;
    Customer anotherCustomer1;

    @BeforeEach
    void setUp() {
        database = new BankDatabase();
        newCustomer =  new Customer("DonDozie", "MoneyMan",
                "Software Engineer", "Sabo", "12345677", "Male", "savings", 1997, 8, 7);

       anotherCustomer =  new Customer("Abdullah", "Ismail",
                "Software Engineer", "Sabo", "12345677", "Male", "current", 2001, 12, 8);

        anotherCustomer1 =  new Customer("Hammend", "Ismail",
                "Software Engineer", "Sabo", "8934983498", "Male", "savings", 2001, 6, 8);
    }

    @AfterEach
    void tearDown() {
        database = null;
        newCustomer = null;
    }

    @Test
    void database_canAddCustomerToDataBase(){
        database.addCustomerToDatabases(newCustomer);
        assertEquals(1, database.getDatabaseLength());

        Customer newCustomer = new Customer("Chibuzo","Angel Gebu", "Software Engineer", "Semicolon", "099329838738732", "Male", "savings", 1950, 1, 1);
        database.addCustomerToDatabases(newCustomer);
        assertEquals(2, database.getDatabaseLength());

        database.addCustomerToDatabases(newCustomer);
        assertEquals(2, database.getDatabaseLength());

    }

    @Test
    void database_canPrintOutASpecificCustomerDetails(){
        database.addCustomerToDatabases(newCustomer);
        database.addCustomerToDatabases(anotherCustomer);
        Customer itExist = database.printCustomerDetails(newCustomer.getAccount().getAccountNumber());
        assertNotNull(itExist);
    }

    @Test
    void database_canDeleteACustomerDetails(){
        database.addCustomerToDatabases(newCustomer);
        database.addCustomerToDatabases(anotherCustomer);
        Customer isDeleted = database.deleteCustomerFromDatabase("Abdullah");
        assertNull(isDeleted);
    }

    @Test
    void database_canUpdateCustomerDetails(){
        database.addCustomerToDatabases(newCustomer);
        database.addCustomerToDatabases(anotherCustomer);
        Customer isUpdated = database.updateCustomerDetails(anotherCustomer.getAccount().getAccountNumber(), anotherCustomer1);
        assertNotNull(isUpdated);
        assertEquals("Hammend", anotherCustomer1.getFirstName());
    }
}