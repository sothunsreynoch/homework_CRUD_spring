package com.example.dataanalyticrestfulwebservice.service.serviceImpl;

import com.example.dataanalyticrestfulwebservice.model.User;
import com.example.dataanalyticrestfulwebservice.model.UserAccount;
import com.example.dataanalyticrestfulwebservice.repository.UserRepo;
import com.example.dataanalyticrestfulwebservice.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserserviceImpl implements UserService {

    UserRepo userRepo;
    UserserviceImpl(UserRepo userRepo){
        this.userRepo = userRepo;
    }
    @Override
    public PageInfo<User> allUsers(int page, int size, String filterName) {

        PageHelper.startPage(page,size);
        new PageInfo<>(userRepo.allUsers(filterName));
        return new PageInfo<>(userRepo.allUsers(filterName)) ;
    }

    @Override
    public List<User> findUserByName() {
        return null;
    }

    @Override
    public User findUserByID(int id) {
        return userRepo.findUserByID(id);
    }

    @Override
    public int createNewUser(User user) {
        return userRepo.createNewUser(user);
    }

    @Override
    public List<UserAccount> getAllUserAccounts() {
        return userRepo.getAllUserAccounts();
    }

    @Override
    public int removeUserById(int id) {
        return userRepo.removeUser(id);
    }

    @Override
    public int updateUserById(User user, int id) {
        return userRepo.updateUserById(user,id);
    }

//    @Override
//    public int updateUserAccountById(UserAccount account, int id) {
//        return userRepo.updateAccountById(account, id);
//    }
}
