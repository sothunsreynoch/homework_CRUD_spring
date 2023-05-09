package com.example.dataanalyticrestfulwebservice.repository;

import com.example.dataanalyticrestfulwebservice.model.Account;
import com.example.dataanalyticrestfulwebservice.model.User;
import com.example.dataanalyticrestfulwebservice.model.UserAccount;
import com.example.dataanalyticrestfulwebservice.repository.provider.UserProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface UserRepo {


//    @Select("select * from users_tb")
//    @Result(column = "id", property = "userId")
    @Result(column = "id", property = "userId")
    @SelectProvider(type = UserProvider.class, method = "getAllUsers")
    List<User> allUsers(String filterName);
    List<User> findUserByUsername(String username);
    @Insert("insert into users_tb (username, gender, address)\n" +
            "values (#{user.username},#{user.gender}, #{user.address})")
    int createNewUser(@Param("user") User user);

    int updateUser(User user);

    @Result(property = "userId", column = "id")
    @Select("select  * from users_tb where id = #{id}")
    User findUserByID(int id );


    @Results({
            @Result(column = "id", property = "userId"),
            @Result(column = "id", property = "accounts", many = @Many(select = "findAccountsByUserId"))
    })
    @Select("select * from users_tb")
    List<UserAccount> getAllUserAccounts();



    @Results({
            @Result(property = "accountName",column = "account_name"),
            @Result(property = "accountNumber", column = "account_no"),
            @Result(property = "phoneNumber", column = "phonenumber"),
            @Result(property ="transferLimit", column = "transfer_limit"),
            @Result(property = "password", column = "passcode"),
            @Result(property = "accountType", column = "account_type",
                    one = @One(select = "com.example.dataanalyticrestfulwebservice.repository.AccountRepo.getAccountTypeByID"))
    })
//    @Select("select * from useraccount_tb " +
//            "    inner join account_tb " +
//            "        a on a.id = useraccount_tb.account_id\n" +
//            "    where user_id = #{id}")
//    List<Account> findAccountsByUserId(int id);
//
//    @Delete("DELETE FROM useraccount_tb WHERE userid = #{id}")
//    int removeAccountById(int id);
//
//    @Update("update useraccount_tb set username = #{account.username}, gender = #{account.male}, " +
//            "profile = '#{account.profile}, pin = #{account.pin}, passcode = #{account.passcode}, " +
//            "phone_number = #{account.phone_number}, transfer_limit = #{account.transfer_limit}, " +
//            "account_types = #{account.account_types} " +
//            "where userid  = #{id}")
//    int updateAccountById(@Param("account") UserAccount account, int id);


    @Select("select * from users_tb " +
            "    inner join account_tb " +
            "        a on a.id = user_tb.account_id\n" +
            "    where user_id = #{id}")
    List<Account> findAccountsByUserId(int id);

    @Delete("DELETE FROM users_tb WHERE id = #{id}")
    int removeUser(int id);

    @Update("update users_tb set username = #{user.username}, gender = #{user.gender}, " +
            "address =#{user.address} where id = #{id}")

    int updateUserById(@Param("user") User user, int id);




}
