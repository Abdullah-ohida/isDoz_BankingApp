package com.bank.database;

import com.bank.customer.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.BooleanSupplier;

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
                "Software Engineer", "Sabo", "12345677", "Male", "savings");

       anotherCustomer =  new Customer("Abdullah", "Ismail", "19/8/1900",
                "Software Engineer", "Sabo", "12345677", "Male", "current");

        anotherCustomer1 =  new Customer("Hammend", "Ismail", "19/8/1900",
                "Software Engineer", "Sabo", "8934983498", "Male", "savings");
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

        Customer newCustomer = new Customer("Chibuzo","Angel Gebu", "12/5/2031", "Software Engineer", "Semicolon", "099329838738732", "Male", "savings");
        database.addCustomerToDatabases(newCustomer);
        assertEquals(2, database.getDatabaseLength());

        database.addCustomerToDatabases(newCustomer);
        assertEquals(2, database.getDatabaseLength());

    }

    @Test
    void database_canPrintOutASpecificCustomerDetails(){
        database.addCustomerToDatabases(newCustomer);
        database.addCustomerToDatabases(anotherCustomer);
        Customer itExist = database.printCustomerDetails("Abdullah");
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
        Customer isUpdated = database.updateCustomerDetails("Abdullah", anotherCustomer1);
        assertNotNull(isUpdated);
        assertEquals("Hammend", anotherCustomer1.getFirstName());
    }
}