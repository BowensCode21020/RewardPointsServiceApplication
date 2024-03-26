package com.example.rewardsservice.controller;

import com.example.rewardsservice.entity.Transaction;
import com.example.rewardsservice.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Endpoint to add a new transaction
    @PostMapping("/add")
    public ResponseEntity<String> addTransaction(@RequestBody Transaction transaction) {
        try {
            // Call the service method to add the transaction and return success response if transaction added successfully
            transactionService.addTransaction(transaction);
            return ResponseEntity.status(HttpStatus.CREATED).body("Transaction added successfully");
        } catch (Exception e) {
            // Handle any unexpected exceptions and return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add transaction");
        }
    }

    // Endpoint to calculate reward points for a customer
    @GetMapping("/calculate-points")
    public ResponseEntity<Integer> calculateRewardPoints(@RequestParam("customerId") String customerId) {
        try {
            // Call the service method to calculate reward points and return the calculated reward points in the response
            int rewardPoints = transactionService.calculateRewardPoints(customerId);
            return ResponseEntity.ok().body(rewardPoints);
        } catch (Exception e) {
            // Handle any unexpected exceptions and return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Endpoint to retrieve all transactions
    @GetMapping("/all")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        try {
            // Call the service method to retrieve all transactions and return the list of transactions in the response
            List<Transaction> transactions = transactionService.getAllTransactions();
            return ResponseEntity.ok().body(transactions);
        } catch (Exception e) {
            // Handle any unexpected exceptions and return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}