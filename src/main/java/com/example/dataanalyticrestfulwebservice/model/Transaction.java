package com.example.dataanalyticrestfulwebservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private  int id;
    private int senderAccountId;
    private int receiverAccount;
    private float amount;
    private String remark;
    private Timestamp transferAt;
}
