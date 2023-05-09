package com.example.dataanalyticrestfulwebservice.model.request;

import com.example.dataanalyticrestfulwebservice.model.User;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserTransaction {
    private int accountId;
    private String accountNumber;
    private User user;
}
