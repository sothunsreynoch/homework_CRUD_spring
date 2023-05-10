package com.example.dataanalyticrestfulwebservice.model;

import com.example.dataanalyticrestfulwebservice.model.request.UserTransaction;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private  int id;
//    private int senderAccountId;
//    private int receiverAccount;
    private UserTransaction sender;
    private UserTransaction receiver;
    private float amount;
    private String remark;
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm")
    private Timestamp transferAt;
}
