package com.example.dataanalyticrestfulwebservice.service.serviceImpl;

import com.example.dataanalyticrestfulwebservice.model.Transaction;
import com.example.dataanalyticrestfulwebservice.repository.TransactionRepo;
import com.example.dataanalyticrestfulwebservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepo transactionRepo;
    @Autowired
    TransactionServiceImpl(TransactionRepo transactionRepo){
        this.transactionRepo = transactionRepo;
    }
    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepo.getAllTransactions();
    }

    @Override
    public int createTransaction(Transaction transaction) {
        return 0;
    }

    @Override
    public int deleteTransaction(int transactionId) {
        return 0;
    }

    @Override
    public List<Transaction> getTransactionByUserId(int userId) {
        return null;
    }
}
