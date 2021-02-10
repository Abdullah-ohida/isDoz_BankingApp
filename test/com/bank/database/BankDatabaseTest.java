package com.bank.database;

import com.bank.customer.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BankDatabaseTest {
    BankDatabase database;

    @BeforeEach
    void setUp() {
        database = new BankDatabase("Union Bank");
    }

    @AfterEach
    void tearDown() {
        database = null;
    }

    @Test
    void database_canAddCustomerToDataBase(){
        Customer newCustomer = new Customer("DonDozie", "MoneyMan", "19/8/1900",
                "Software Engineer", "Sabo", "12345677", "Male");

        database.addCustomerToDatabases(newCustomer);
        assertEquals(1, database.getDatabaseLength());

    }
}