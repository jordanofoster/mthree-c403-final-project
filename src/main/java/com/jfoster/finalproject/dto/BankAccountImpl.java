package com.jfoster.finalproject.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidParameterException;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import org.hibernate.annotations.processing.Pattern;

/**
 * Class implementation of the <code>BankAccount</code> interface. Classed as a JPA entity.
 * @author Jordan Foster
 * @version 1
 */
@Entity(name="accounts")
public class BankAccountImpl implements BankAccount {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long accountNumber;

    @ManyToOne
    @JsonProperty("account_owner")
    @JoinColumn(name = "account_owner", nullable = false)
    private BankCustomerImpl accountOwner;

    @JsonProperty("sort_code")
    @Column(nullable = false)
    private String sortCode;

    @JsonProperty("iban")
    @Column(nullable = false)
    private String iban;

    @Column(nullable = false)
    private BigDecimal balance = new BigDecimal("0.00").setScale(2, RoundingMode.HALF_UP);

    @JsonProperty("max_overdraft")
    @Column(nullable = false)
    private BigDecimal maxOverdraft;

    /*@OneToMany(mappedBy="transactionTimestamp")
    @Column(nullable = false)
    private List<BankTransactionImpl> transactions = new Vector<BankTransactionImpl>();*/

    public BankAccountImpl(){}

    /**
     * Constructor for <code>BankAccountImpl.</code> All accounts are initialised with an empty balance; the expectation is that the user then modifies this value through class methods.
     *
     * @param account_owner Foreign key that represents a <code>BankCustomer</code> object (or record id from the <code>customers</code> table.
     * @param sort_code     Sort code of the bank account. Must follow the format of [0-9]{2}-[0-9]{2}-[0-9]{2}.
     * @param iban          IBAN of the account.
     * @param max_overdraft Maximum overdraft of the account.
     */
    public BankAccountImpl(BankCustomerImpl account_owner, String sort_code, String iban, String max_overdraft) {
        this.accountOwner = account_owner;
        this.sortCode = sort_code;
        this.iban = iban;
        this.maxOverdraft = new BigDecimal(max_overdraft).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public Long getAccountNumber() {
        return this.accountNumber;
    }

    @Override
    public BankCustomerImpl getAccountOwner() {
        return this.accountOwner;
    }

    @Override
    public String getSortCode() {
        return this.sortCode;
    }

    @Override
    public String getIban() {
        return this.iban;
    }

    /**
     * Gets the balance of the account.
     *
     * @return Returns the balance in the account as a BigDecimal object with a scale of 2 and rounding mode of HALF_UP.
     */
    @Override
    public BigDecimal getBalance() {
        return this.balance;
    }

    @Override
    public BigDecimal getMaxOverdraft() {
        return this.maxOverdraft;
    }

    @Override
    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    @Override
    public void setIban(String iban) {
        this.iban = iban;
    }

    @Override
    public void setBalance(BigDecimal newBalance) {
        this.balance = newBalance.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public void setMaxOverdraft(BigDecimal maxOverdraft) {
        this.maxOverdraft = maxOverdraft.setScale(2, RoundingMode.HALF_UP);
    }

}
