package com.jfoster.finalproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity(name="customers")
public class BankCustomerImpl implements BankCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("customer_reference_number")
    @Column(nullable = false)
    private Long customerReferenceNumber;

    @JsonProperty("name")
    @Column(nullable = false)
    private String name;

    @JsonProperty("address")
    @Column(nullable = false)
    private String address;

    @JsonProperty("phone_number")
    @Column(nullable = false)
    private String phoneNumber;

    @JsonProperty("email")
    @Column(nullable = false)
    private String email;

    public BankCustomerImpl(){}

    public BankCustomerImpl(String name, String address, String phoneNumber, String email) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Override
    public Long getCustomerReferenceNumber() {
        return this.customerReferenceNumber;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getAddress() {
        return this.address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }
}
