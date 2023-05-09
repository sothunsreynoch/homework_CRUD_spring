package com.example.dataanalyticrestfulwebservice.service;

import com.example.dataanalyticrestfulwebservice.model.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getAllTransactions();
    int createTransaction(Transaction transaction);
    int deleteTransaction(int transactionId);
    List<Transaction> getTransactionByUserId(int userId);
}
