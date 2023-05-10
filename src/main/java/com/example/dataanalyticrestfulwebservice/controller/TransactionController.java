package com.example.dataanalyticrestfulwebservice.controller;

import com.example.dataanalyticrestfulwebservice.model.Transaction;
import com.example.dataanalyticrestfulwebservice.service.TransactionService;
import com.example.dataanalyticrestfulwebservice.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
   private final TransactionService transactionService;
   @Autowired
    TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }
    @GetMapping("/all-transactions")
    public Response<List<Transaction>> getAllTransactions(){
       List<Transaction> transactions = transactionService.getAllTransactions();
        return Response.<List<Transaction>>ok().setPayload(transactions)
                .setMessage("Successfully retrieved all the transaction..!");
    }
}
