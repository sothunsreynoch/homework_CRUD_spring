package com.example.dataanalyticrestfulwebservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserAccount {

    private int userId;
    private String username;
    private String gender;
    private String address;
//    private User user;
    private List<Account> accounts;
}
