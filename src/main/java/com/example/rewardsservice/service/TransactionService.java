package com.example.rewardsservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import com.example.rewardsservice.entity.Transaction;
import com.example.rewardsservice.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public void addTransaction(Transaction transaction) {
        if (transaction != null) {
            transactionRepository.save(transaction);
        }
    }

    public int calculateRewardPoints(String customerId) {
        try {
            List<Transaction> customerTransactions = transactionRepository.findByCustomerId(customerId);
            int totalRewardPoints = 0;
            for (Transaction transaction : customerTransactions) {
                totalRewardPoints += calculateRewardPointsForTransaction(transaction.getAmount());
            }
            return totalRewardPoints;
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return -1;
        }
    }

    private int calculateRewardPointsForTransaction(BigDecimal amount) {
        // Exception handling for invalid amount parameter
        if (amount == null) {
            throw new IllegalArgumentException("Transaction amount cannot be null");
        }

        // Calculation logic
        BigDecimal over100Threshold = new BigDecimal(100);
        BigDecimal between50And100Threshold = new BigDecimal(50);

        BigDecimal pointsOver100 = amount.subtract(over100Threshold).max(BigDecimal.ZERO).multiply(new BigDecimal(2));
        BigDecimal pointsBetween50And100 = amount.min(over100Threshold).max(between50And100Threshold).subtract(between50And100Threshold).max(BigDecimal.ZERO);

        return pointsOver100.intValue() + pointsBetween50And100.intValue();
    }

    public List<Transaction> getAllTransactions() {
        try {
            return transactionRepository.findAll();
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
