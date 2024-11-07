package com.jfoster.finalproject.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import jakarta.persistence.*;
import jakarta.persistence.GenerationType;


@Entity(name="accounts")
public class BankAccountImpl {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int account_number;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable=false)
    private BankCustomer account_owner;

    @Column(nullable = false)
    private String sort_code;

    @Column(nullable = false)
    private String iban;

    @Column(nullable = false)
    private BigDecimal balance = new BigDecimal("0.00").setScale(2,RoundingMode.HALF_UP);

    @Column(nullable = false)
    private BigDecimal max_overdraft;

    public BankAccountImpl(BankCustomer account_owner, String sort_code, String iban, String max_overdraft) {
        this.account_owner = account_owner;
        this.sort_code = sort_code;
        this.iban = iban;
        this.max_overdraft = new BigDecimal(max_overdraft).setScale(2,RoundingMode.HALF_UP);
    }

    void deposit(BigDecimal amount) {
        throw new UnsupportedOperationException("Method not implemented.");
    }

    void withdraw(BigDecimal amount) {
        throw new UnsupportedOperationException("Method not implemented.");
    }

    private void setBalance(BigDecimal amount) {
        throw new UnsupportedOperationException("Method not implemented.");
    }
}
