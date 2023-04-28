package com.example.dataanalyticrestfulwebservice.service;

import com.example.dataanalyticrestfulwebservice.model.User;
import com.example.dataanalyticrestfulwebservice.model.UserAccount;
import org.springframework.stereotype.Service;

import java.util.List;
public interface UserService {
    List<User> allUsers();
    List<User> findUserByName();
    User findUserByID(int id);
    int createNewUser(User user);

    List<UserAccount> getAllUserAccounts();
    int removeUserById(int id);
    int updateUserById(User user, int id);


}
