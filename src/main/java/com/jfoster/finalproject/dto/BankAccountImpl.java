package com.jfoster.finalproject.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidParameterException;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.*;

/**
 * DTO Class implementation that represents a bank account record within the accounts table.
 */
@Entity(name="accounts")
public class BankAccountImpl implements BankAccount {


    /**
     * Primary key of the schema. Represented as BIGINT in the underlying DB.
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long accountNumber;

    /**
     * Foreign key representing the BankCustomer record that owns this account.
     * Must not be null.
     */
    @ManyToOne
    @JsonProperty("account_owner")
    @NotNull
    @JoinColumn(name = "account_owner", nullable = false)
    private BankCustomerImpl accountOwner;

    /**
     * String representing the sort code of the account.
     * Must be of exactly 8 characters long, and must match the regex pattern of <code>^\d{2}-\d{2}-\d{2}$</code>.
     * Must not be null.
     */
    @JsonProperty("sort_code")
    @Size(min=8,max=8)
    @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{2}$")
    @NotNull
    @Column(nullable = false)
    private String sortCode;

    /**
     * String representing the IBAN of the account.
     * Must be no longer than 34 characters, and must match the regex pattern of <code>[A-Z]{2}\d{2}\d{1,30}$</code>.
     * Must not be null.
     */
    @JsonProperty("iban")
    @Size(max=34)
    @NotNull
    @Pattern(regexp = "^[A-Z]{2}\\d{2}\\d{1,30}$")
    @Column(nullable = false)
    private String iban;

    /**
     * BigDecimal representing the balance of the account.
     * May have no more than 19 integer digits and two fractional digits, in line with underlying schema of DECIMAL(19,2).
     * Cannot be null.
     * Initialised to 0.00 by default; any further fractional digits are assumed to be rounded up.
     */
    @NotNull
    @Digits(integer=19, fraction=2)
    @Column(nullable = false)
    private BigDecimal balance = new BigDecimal("0.00").setScale(2, RoundingMode.HALF_UP);

    /**
     * BigDecimal representing the maximum overdraft of the account.
     * May not be a negative value.
     * Cannot be null.
     * May have no more than 19 integer digits and two fractional digits, in line with underlying schema of DECIMAL(19,2).
     */
    @JsonProperty("max_overdraft")
    @PositiveOrZero
    @NotNull
    @Digits(integer=19, fraction=2)
    @Column(nullable = false)
    private BigDecimal maxOverdraft;

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

    /**
     * Gets the account number of the entity.
     * @return Long object representing the account number (represented within accounts as BIGINT).
     */
    @Override
    public Long getAccountNumber() {
        return this.accountNumber;
    }

    /**
     * Gets the owner of the account.
     * @return BankCustomer object representing the owner of the account (represented within accounts as BankCustomer's primary key).
     */
    @Override
    public BankCustomerImpl getAccountOwner() {
        return this.accountOwner;
    }

    /**
     * Gets the sort code of the account.
     * @return String object representing the sort code of the account (represented within accounts as CHAR(8))..
     */
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

    /**
     * Gets the maximum overdraft of the account
     * @return BigDecimal(2, HALF_UP) object representing the maximum overdraft (represented within accounts as DECIMAL(19,2).
     */
    @Override
    public BigDecimal getMaxOverdraft() {
        return this.maxOverdraft;
    }

    /**
     * Sets the sort code of the account.
     * @param sortCode String to set the entity variable to.
     */
    @Override
    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    /**
     * Sets the IBAN of the account
     * @param iban String to set the entity variable to.
     */
    @Override
    public void setIban(String iban) {
        this.iban = iban;
    }

    /**
     * sets the balance of the account.
     * Notably, this should only be used by the service layer after validation checks on debit/credit operations pass.
     * @param newBalance BigDecimal object representing the new balance. This will be subject to .setScale(2,HALF_UP).
     */
    @Override
    public void setBalance(BigDecimal newBalance) {
        this.balance = newBalance.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Sets the max overdraft of the account.
     * @param maxOverdraft BigDecimal object representing the new overdraft. This will be subject to .setScale(2,HALF_UP).
     * @throws InvalidParameterException Thrown when the new overdraft provided would result in the current balance exceeding the maximum allowable overdraft.
     * (This might need to be refactored into the service layer).
     */
    @Override
    public void setMaxOverdraft(BigDecimal maxOverdraft) {
        this.maxOverdraft = maxOverdraft.setScale(2, RoundingMode.HALF_UP);
    }

}
