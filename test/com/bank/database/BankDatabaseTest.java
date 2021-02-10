package com.bank.database;

import com.bank.customer.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BankDatabaseTest {
    BankDatabase database;
    Customer newCustomer;
    Customer anotherCustomer;

    @BeforeEach
    void setUp() {
        database = new BankDatabase("Union Bank");
        newCustomer =  new Customer("DonDozie", "MoneyMan", "19/8/1900",
                "Software Engineer", "Sabo", "12345677", "Male");

       anotherCustomer =  new Customer("Abdullah", "Ismail", "19/8/1900",
                "Software Engineer", "Sabo", "12345677", "Male");
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
        database.printCustomerDetails("Abdullah");
        database.printCustomerDetails("DonDozie");

        assertEquals(2, database.getDatabaseLength());
    }
}