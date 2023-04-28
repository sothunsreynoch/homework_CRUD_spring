package com.example.dataanalyticrestfulwebservice.model.response;

import com.example.dataanalyticrestfulwebservice.model.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
    private int id;
    private String accountName;
    private String accountNumber;
    private String profile;
    private String phoneNumber;
    private int transferLimit;
//    private int accountType;
    private AccountType accountType;

}
