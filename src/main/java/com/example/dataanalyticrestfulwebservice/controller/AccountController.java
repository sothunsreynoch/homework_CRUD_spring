package com.example.dataanalyticrestfulwebservice.controller;


import com.example.dataanalyticrestfulwebservice.mapper.AutoAccountMapper;
import com.example.dataanalyticrestfulwebservice.model.Account;
import com.example.dataanalyticrestfulwebservice.model.response.AccountResponse;
import com.example.dataanalyticrestfulwebservice.repository.AccountRepo;
import com.example.dataanalyticrestfulwebservice.service.AccountService;
import com.example.dataanalyticrestfulwebservice.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    final private AccountService accountService;
    final private AutoAccountMapper autoAccountMapper;
    AccountController(AccountService accountService, AutoAccountMapper autoAccountMapper){
        this.accountService = accountService;
        this.autoAccountMapper = autoAccountMapper;
    }
    @GetMapping("/allAccounts")
    public Response<List<AccountResponse>> getAllAccounts(){
        try {
            List<Account> allAccount = accountService.getAllAccounts();

            List<AccountResponse> accountResponse = autoAccountMapper.mapToAccountResponse(allAccount);

//            HashMap<String , Object> response = new HashMap<>();
//            response.put("payload" , allAccount);
//            response.put("message","Successfully retrieve all accounts info! ");
//            response.put("status", HttpStatus.OK);


            return Response.<List<AccountResponse>>ok().setPayload(accountResponse).setMessage("successfully retrieved all account information");

        }catch (Exception ex){
            System.out.println("Something wrong : " + ex.getMessage());
//            return ResponseEntity.ok().body("Failed to retrieved the accounts");
            return Response.<List<AccountResponse>>exception().setMessage("Exception occurs! feild");
        }
    }
}

