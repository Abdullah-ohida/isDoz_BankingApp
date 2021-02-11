package com.bank.customer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    Customer newCustomer;

    @BeforeEach
    void setUp() {
        newCustomer = new Customer("DonDozie", "MoneyMan", "19/8/1900",
                "Software Engineer", "Sabo", "12345677", "Male", "savings");
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
    void customer_hasADateOfBirth(){
        assertNotNull(newCustomer.getDateOfBirth());
    }

    @Test
    void customer_canSetDataOfBirth(){
        newCustomer.setDateOfBirth("7/8/2001");
        assertEquals("7/8/2001", newCustomer.getDateOfBirth());
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
        System.out.println(newCustomer.displayUser());
       assertEquals("Male", newCustomer.getGender());
    }

    @Test
    void customer_hasAccountNumber(){
        assertNotNull(newCustomer.getAccount().getAccountNumber());
    }



}