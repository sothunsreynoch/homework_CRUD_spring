package com.example.dataanalyticrestfulwebservice.service.serviceImpl;

import com.example.dataanalyticrestfulwebservice.model.Account;
import com.example.dataanalyticrestfulwebservice.repository.AccountRepo;
import com.example.dataanalyticrestfulwebservice.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountServiceImpl implements AccountService {
    final private AccountRepo accountRepo;
    AccountServiceImpl(AccountRepo accountRepo){
        this.accountRepo = accountRepo;
    }
    @Override
    public List<Account> getAllAccounts() {
        return accountRepo.getAllAccounts();
    }

    @Override
    public int createAccount(Account account) {
        return 0;
    }

    @Override
    public int updateAccount(Account account, int id) {
        return 0;
    }

    @Override
    public Account findAccountByID(int id) {
        return accountRepo.findAccountByID(id);
    }
}
