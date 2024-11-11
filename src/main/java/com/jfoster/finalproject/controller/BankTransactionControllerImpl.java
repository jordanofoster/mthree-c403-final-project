package com.jfoster.finalproject.controller;

import com.jfoster.finalproject.dto.BankTransactionImpl;
import com.jfoster.finalproject.service.BankTransactionServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Class implementation for the Spring MVC controller responsible for presenting the BankTransaction REST API.
 */
@RestController
public class BankTransactionControllerImpl implements BankTransactionController {


    /**
     * Autowired service bean that permits communication with the service layer of the BankTransaction MVC substack.
     */
    @Autowired
    private BankTransactionServiceImpl bankTransactionService;

    /**
     * initBinder for use in interpreting Timestamp objects.
     * @param binder binding object used to properly interpret Timestamp objects.
     */
    @InitBinder
    @Override
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Timestamp.class, new PropertyEditorSupport() {
            private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    setValue(new Timestamp(dateFormat.parse(text).getTime()));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });
    }

    /**
     * REST API endpoint for creating a transaction.
     * @param bankTransactionObj deserialized BankTransaction object to be added to the database.
     * @return ResponseEntity containing the BankTransaction object as it appears in the transactions table (i.e., with generated timestamp).
     */
    @Override
    public ResponseEntity<BankTransactionImpl> createTransaction(@Valid  @RequestBody BankTransactionImpl bankTransactionObj) {
        return new ResponseEntity<BankTransactionImpl>(bankTransactionService.createTransaction(bankTransactionObj), HttpStatus.CREATED);
    }

    /**
     * REST API endpoint for getting all transactions.
     * @return ResponseEntity containing a list of JSON objects representing all BankTransaction objects within the transactions table.
     */
    @Override
    public ResponseEntity<List<BankTransactionImpl>> getAllTransactions() {
        return new ResponseEntity<List<BankTransactionImpl>>(bankTransactionService.getAllTransactions(), HttpStatus.OK);
    }

    /**
     * REST API endpoint for getting a specific transaction.
     * @param timestamp timestamp of the transaction to be retrieved, defined via the API endpoint.
     * @return ResponseEntity containing a JSON object representing the BankTransaction object as it appears in the transactions table.
     */
    @Override
    public ResponseEntity<BankTransactionImpl> getTransactionByTimestamp(@Valid @PathVariable("timestamp") Timestamp timestamp) {
        return new ResponseEntity<BankTransactionImpl>(bankTransactionService.getTransactionByTimestamp(timestamp), HttpStatus.OK);
    }

    /**
     * REST API endpoint for updating the transaction method used in a previously-recorded transaction.
     * @param timestamp timestamp of the transaction for which the method will be updated, defined via the API endpoint.
     * @param transactionMethod String representing the new transaction method to be updated within the BankTransaction record.
     * @return ResponseEntity containing a JSON object representing the updated BankTransaction object as it appears in the transactions table.
     */
    @Override
    public ResponseEntity<BankTransactionImpl> updateTransactionMethod(@Valid @PathVariable("timestamp") Timestamp timestamp, @Valid @RequestParam(value = "method") String transactionMethod) {
        return new ResponseEntity<BankTransactionImpl>(bankTransactionService.updateTransactionMethod(timestamp, transactionMethod), HttpStatus.OK);
    }

}
