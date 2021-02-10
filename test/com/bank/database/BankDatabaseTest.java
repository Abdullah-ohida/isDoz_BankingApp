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
        database = new BankDatabase("Union Bank");
        newCustomer =  new Customer("DonDozie", "MoneyMan", "19/8/1900",
                "Software Engineer", "Sabo", "12345677", "Male");

       anotherCustomer =  new Customer("Abdullah", "Ismail", "19/8/1900",
                "Software Engineer", "Sabo", "12345677", "Male");

        anotherCustomer1 =  new Customer("Hammend", "Ismail", "19/8/1900",
                "Software Engineer", "Sabo", "8934983498", "Male");
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
    }

    @Test
    void database_canPrintOutASpecificCustomerDetails(){
        database.addCustomerToDatabases(newCustomer);
        database.addCustomerToDatabases(anotherCustomer);
        boolean itExist = database.printCustomerDetails("Abdullah");
        assertTrue(itExist);
        assertEquals(2, database.getDatabaseLength());
    }

    @Test
    void database_canDeleteACustomerDetails(){
        database.addCustomerToDatabases(newCustomer);
        database.addCustomerToDatabases(anotherCustomer);
        boolean isDeleted = database.deleteCustomerFromDatabase("Abdullah");
        assertTrue(isDeleted);
        assertEquals(1, database.getDatabaseLength());
    }

    @Test
    void database_canUpdateCustomerDetails(){
        database.addCustomerToDatabases(newCustomer);
        database.addCustomerToDatabases(anotherCustomer);

        boolean isUpdated = database.updateCustomerDetails("Abdullah", anotherCustomer1);
        assertEquals("Hammend", anotherCustomer1.getFirstName());
    }
}