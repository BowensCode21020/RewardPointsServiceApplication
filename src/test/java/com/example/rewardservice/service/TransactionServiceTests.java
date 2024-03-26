package com.example.rewardservice.service;

import com.example.rewardsservice.entity.Transaction;
import com.example.rewardsservice.repository.TransactionRepository;
import com.example.rewardsservice.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionServiceTests {
    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddTransaction() {
        // Create a transaction
        Transaction transaction = new Transaction("1", BigDecimal.valueOf(100), LocalDate.of(2024, 3, 25));

        // Call the service method
        transactionService.addTransaction(transaction);

        // Verify that the repository's save method is called once with the transaction
        verify(transactionRepository, times(1)).save(transaction);
    }

    @ParameterizedTest
    @ValueSource(strings = {"50", "80", "100", "150", "200", "250"})
    public void testCalculateRewardPoints(String amountString) {
        // Mock data
        BigDecimal amount = new BigDecimal(amountString);
        int expectedPoints = transactionService.calculateRewardPoints(amountString);

        // Call the service method
        int actualPoints = transactionService.calculateRewardPoints("1");

        // Verify the result
        assertEquals(expectedPoints, actualPoints);
    }

    @ParameterizedTest
    @MethodSource("transactionProvider")
    public void testGetAllTransactions(List<Transaction> transactions) {
        // Mocking repository method
        when(transactionRepository.findAll()).thenReturn(transactions);

        // Call the service method
        List<Transaction> retrievedTransactions = transactionService.getAllTransactions();

        // Verify the result
        assertEquals(transactions, retrievedTransactions);
    }

    private static List<List<Transaction>> transactionProvider() {
        // Prepare different sets of transactions for testing
        List<Transaction> transactions1 = Arrays.asList(
                new Transaction("1", BigDecimal.valueOf(100), LocalDate.of(2024, 3, 25)),
                new Transaction("2", BigDecimal.valueOf(200), LocalDate.of(2024, 3, 26))
        );
        List<Transaction> transactions2 = Arrays.asList(
                new Transaction("3", BigDecimal.valueOf(150), null),
                new Transaction("4", BigDecimal.valueOf(250), null)
        );


        // Return the sets of transactions
        return Arrays.asList(transactions1, transactions2);
    }

    @Test
    public void testAddTransactionWithNullTransaction() {
        // Call the service method with a null transaction
        transactionService.addTransaction(null);

        // Verify that the repository's save method is not called
        verify(transactionRepository, never()).save(any());
    }

    @Test
    public void testCalculateRewardPointsWithNegativeAmount() {
        // Call the service method with a negative amount
        int points = transactionService.calculateRewardPoints("-100");

        // Verify that the method returns 0 points for negative amounts
        assertEquals(0, points);
    }

    @Test
    public void testCalculateRewardPointsWithZeroAmount() {
        // Call the service method with a zero amount
        int points = transactionService.calculateRewardPoints("0");

        // Verify that the method returns 0 points for zero amounts
        assertEquals(0, points);
    }

    @Test
    public void testGetAllTransactionsWithEmptyList() {
        // Mock the repository to return an empty list
        when(transactionRepository.findAll()).thenReturn(Collections.emptyList());

        // Call the service method
        List<Transaction> retrievedTransactions = transactionService.getAllTransactions();

        // Verify that an empty list is returned
        assertTrue(retrievedTransactions.isEmpty());
    }

    @Test
    public void testGetAllTransactionsWithNullList() {
        // Mock the repository to return null
        when(transactionRepository.findAll()).thenReturn(null);

        // Call the service method
        List<Transaction> retrievedTransactions = transactionService.getAllTransactions();

        // Verify that null is returned
        assertNull(retrievedTransactions);
    }

    @Test
    public void testGetAllTransactionsWithNullDate() {
        // Prepare transactions with one having a null date
        List<Transaction> transactions = Arrays.asList(
                new Transaction("1", BigDecimal.valueOf(100), LocalDate.of(2024, 3, 25)),
                new Transaction("2", BigDecimal.valueOf(200), null)
        );

        // Mock repository method
        when(transactionRepository.findAll()).thenReturn(transactions);

        // Call the service method
        List<Transaction> retrievedTransactions = transactionService.getAllTransactions();

        // Verify the result
        assertEquals(transactions, retrievedTransactions);
    }
}