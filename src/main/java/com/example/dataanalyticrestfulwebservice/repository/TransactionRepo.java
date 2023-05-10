package com.example.dataanalyticrestfulwebservice.repository;

import com.example.dataanalyticrestfulwebservice.model.Transaction;
import com.example.dataanalyticrestfulwebservice.model.request.UserTransaction;
import com.example.dataanalyticrestfulwebservice.repository.provider.TransactionProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TransactionRepo {

    @SelectProvider(type = TransactionProvider.class, method = "getTransactions")

    @Results({
            @Result(column = "sender_account_id", property ="sender",one = @One(select = "getUserTransactionByAccountID")),
            @Result(column = "receiver_account_id", property ="receiver" , one = @One(select="getUserTransactionByAccountID")),
            @Result(column = "transfer_at", property ="transferAt" )
    })
    List<Transaction> getAllTransactions();


    @Select("select account_no, account_id, ut.* from useraccount_tb\n" +
            "           inner join users_tb ut on ut.id = useraccount_tb.user_id\n" +
            "            inner join account_tb a on a.id = useraccount_tb.account_id\n" +
            "           where account_id = #{id}")
    @Results(value = {
            @Result(property ="accountId", column ="account_id"),
            @Result(property = "accountNumber",column = "account_no"),
            @Result(property = "user.userId", column = "id"),
            @Result(property = "user.username",column = "username"),
            @Result(property = "user.gender", column = "gender"),
            @Result(property = "user.address", column = "address")
    })
    UserTransaction getUserTransactionByAccountID(int id);
}
