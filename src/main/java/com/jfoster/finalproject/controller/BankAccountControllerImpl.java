package com.jfoster.finalproject.controller;

import com.jfoster.finalproject.dao.BankAccountRepository;
import com.jfoster.finalproject.dto.BankAccountImpl;
import com.jfoster.finalproject.dto.BankCustomerImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class BankAccountControllerImpl implements BankAccountController {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public ResponseEntity<BankAccountImpl> createAccount(BankAccountImpl bankAccount) {
        try {
            BankAccountImpl createdAccount = bankAccountRepository.saveAndFlush(bankAccount);
            return new ResponseEntity<BankAccountImpl>(createdAccount, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BankAccountImpl>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<BankAccountImpl>> getAllAccounts() {
        try {
            List<BankAccountImpl> foundAccounts = bankAccountRepository.findAll();
            return new ResponseEntity<List<BankAccountImpl>>(foundAccounts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<BankAccountImpl>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<BankAccountImpl> getAccountById(long id) {
        try {
            BankAccountImpl foundAccount =  bankAccountRepository.findById(id).orElseThrow();
            return new ResponseEntity<BankAccountImpl>(foundAccount, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<BankAccountImpl>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<BankAccountImpl>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<BankAccountImpl> updateAccount(BankAccountImpl updatedBankAccountObj, long id) {
        try {
            BankAccountImpl internalBankAccountObj = bankAccountRepository.findById(id).orElseThrow();

            if (internalBankAccountObj.getAccountNumber().equals(updatedBankAccountObj.getAccountNumber())) {
                internalBankAccountObj.setSortCode(updatedBankAccountObj.getSortCode());
                internalBankAccountObj.setIban(updatedBankAccountObj.getIban());
                internalBankAccountObj.setBalance(updatedBankAccountObj.getBalance());
                internalBankAccountObj.setMaxOverdraft(updatedBankAccountObj.getMaxOverdraft());

                updatedBankAccountObj = bankAccountRepository.saveAndFlush(internalBankAccountObj);

                return new ResponseEntity<BankAccountImpl>(updatedBankAccountObj, HttpStatus.OK);
            } else { throw new NoSuchElementException(); }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<BankAccountImpl>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<BankAccountImpl>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void deleteAccountById(long id) {
        bankAccountRepository.deleteById(id);
    }
}
