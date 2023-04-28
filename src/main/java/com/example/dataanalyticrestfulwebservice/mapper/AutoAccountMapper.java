package com.example.dataanalyticrestfulwebservice.mapper;

import com.example.dataanalyticrestfulwebservice.model.Account;
import com.example.dataanalyticrestfulwebservice.model.response.AccountResponse;
import com.example.dataanalyticrestfulwebservice.repository.AccountRepo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AutoAccountMapper {
    List<AccountResponse > mapToAccountResponse(List<Account> accounts);

    List<Account> mapToAccount(List<AccountResponse> responses);
}
