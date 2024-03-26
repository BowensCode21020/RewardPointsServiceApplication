package com.example.rewardsservice.data;

import com.example.rewardsservice.entity.Transaction;
import com.example.rewardsservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void run(String... args) throws Exception {
        // Insert mock data into the database
        Transaction transaction1 = new Transaction("123", BigDecimal.valueOf(100), LocalDate.parse("2024-03-28"));
        Transaction transaction2 = new Transaction("456", BigDecimal.valueOf(150), LocalDate.parse("2024-03-27"));
        transactionRepository.saveAll(Arrays.asList(transaction1, transaction2));
    }
}
