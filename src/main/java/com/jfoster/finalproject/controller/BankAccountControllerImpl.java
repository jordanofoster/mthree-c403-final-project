package com.jfoster.finalproject.controller;

import com.jfoster.finalproject.dto.BankAccountImpl;
import com.jfoster.finalproject.service.BankAccountServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Implementation class for the Spring MVC controller responsible for presenting the BankAccount REST API.
 */
@RestController
public class BankAccountControllerImpl implements BankAccountController {

    /**
     * Autowired service bean that permits communication with the service layer of the BankAccount MVC substack.
     */
    @Autowired
    private BankAccountServiceImpl bankAccountService;

    /**
     * REST API endpoint for creating an account.
     * @param bankAccount deserialized bankAccount object to be added to the database.
     * @return ResponseEntity containing the new bankAccount object as it appears in the accounts table (i.e., with generated ID).
     */
    @Override
    public ResponseEntity<BankAccountImpl> createAccount(@Valid  @RequestBody BankAccountImpl bankAccount) {
        return new ResponseEntity<BankAccountImpl>(bankAccountService.createAccount(bankAccount), HttpStatus.CREATED);
    }

    /**
     * REST API endpoint for getting all accounts.
     * @return ResponseEntity containing a list of JSON objects representing all BankAccount objects within the accounts table.
     */
    @Override
    public ResponseEntity<List<BankAccountImpl>> getAllAccounts() {
        return new ResponseEntity<List<BankAccountImpl>>(bankAccountService.getAllAccounts(), HttpStatus.OK);
    }

    /**
     * REST API endpoint for getting a specific account.
     * @param id ID of the bank account to be retrieved, defined via the API endpoint.
     * @return ResponseEntity containing a JSON object representing the BankAccount object as it appears in the accounts table.
     */
    @Override
    public ResponseEntity<BankAccountImpl> getAccountById(@Valid @PathVariable("id") long id) {
        return new ResponseEntity<BankAccountImpl>(bankAccountService.getAccountById(id), HttpStatus.OK);
    }

    /**
     * REST API endpoint for depositing funds into an account ("debiting").
     * @param id Id of the bank account to be debited, defined via the API endpoint.
     * @param amount currency amount to be deposited into the account.
     * @return BigDecimal representing the new balance of the account provided.
     */
    @Override
    public ResponseEntity<BigDecimal> credit(@Valid @PathVariable("id") long id, @Valid @RequestParam(value = "amount") BigDecimal amount) {
        return new ResponseEntity<BigDecimal>(bankAccountService.credit(id, amount), HttpStatus.OK);
    }

    /**
     * REST API endpoint for withdrawing funds from an account ("crediting.")
     * @param id Id of the bank account to be credited, defined via the API endpoint.
     * @param amount currency amount to be credited from the account.
     * @return BigDecimal representing the new balance of the account provided.
     */
    @Override
    public ResponseEntity<BigDecimal> debit(@Valid @PathVariable("id") long id, @Valid @RequestParam(value = "amount") BigDecimal amount) {
        return new ResponseEntity<BigDecimal>(bankAccountService.debit(id, amount), HttpStatus.OK);
    }

    /**
     * REST API endpoint for defining a new maximum overdraft (negative balance) for an account.
     * @param id Id of the bank account for which the overdraft will be modified, defined via the API endpoint.
     * @param overdraft currency amount that represents the new maximum overdraft.
     * @return BigDecimal representing the new maximum overdraft of the account provided.
     */
    @Override
    public ResponseEntity<BigDecimal> updateMaxOverdraft(@Valid @PathVariable("id") long id, @Valid @RequestParam(value = "overdraft") BigDecimal overdraft) {
        return new ResponseEntity<BigDecimal>(bankAccountService.updateMaxOverdraft(id, overdraft), HttpStatus.OK);
    }

    /**
     * REST API endpoint for deleting an existing account.
     * @param id Id of the bank account to be deleted.
     */
    @Override
    public void deleteAccountById(@Valid @PathVariable("id") long id) {
        bankAccountService.deleteAccountById(id);
    }

}
