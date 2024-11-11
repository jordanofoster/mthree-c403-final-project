package com.jfoster.finalproject.dto;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class BankCustomerImplTest {

    private String name,address,phoneNumber,email;
    private BankCustomerImpl testCustomer;

    public BankCustomerImplTest() {
        name = "John Doe";
        address = "18 Fictional Way";
        phoneNumber = "+440821324232";
        email = "johnd@example.com";
    }

    @BeforeEach
    public void beforeEach() {
        testCustomer = new BankCustomerImpl(
                name,
                address,
                phoneNumber,
                email
        );
    }

    @Test
    public void testGetName() {
        assertEquals(testCustomer.getName(),name);
    }

    @Test
    public void testSetName() {
        String newName = "Jane Doe";

        testCustomer.setName(newName);
        assertEquals(testCustomer.getName(),newName);
    }

    @Test
    public void testGetPhoneNumber() {
        assertEquals(testCustomer.getPhoneNumber(), phoneNumber);
    }

    @Test
    public void testSetPhoneNumber() {
        String newNumber = "+450821324232";

        testCustomer.setPhoneNumber(newNumber);
        assertEquals(testCustomer.getPhoneNumber(), newNumber);
    }

    @Test
    public void testGetEmail() {
        assertEquals(testCustomer.getEmail(), email);
    }

    @Test
    public void testSetEmail() {
        String newEmail = "janedoe@example.com";

        testCustomer.setEmail(newEmail);
        assertEquals(testCustomer.getEmail(), newEmail);
    }

    @Test
    public void testGetAddress() {
        assertEquals(testCustomer.getAddress(), address);
    }

    @Test
    public void testSetAddress() {
        String newAddress = "19 Fictional Way";

        testCustomer.setAddress(newAddress);
        assertEquals(testCustomer.getAddress(), newAddress);
    }

}
