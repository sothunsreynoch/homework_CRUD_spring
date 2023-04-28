package com.example.dataanalyticrestfulwebservice.controller;

import com.example.dataanalyticrestfulwebservice.model.User;
import com.example.dataanalyticrestfulwebservice.model.UserAccount;
import com.example.dataanalyticrestfulwebservice.service.UserService;
import com.example.dataanalyticrestfulwebservice.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {
    private final UserService userService;
    UserRestController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/allUsers")
    public List<User> getAllUsers(){
        return userService.allUsers();
    }

    @GetMapping("/{id}")
    public User findUserByID(@PathVariable int id){
        return userService.findUserByID(id);
    }

    @PostMapping("/new-user")
    public String createUser(@RequestBody User user){
        try {
            int affectRow = userService.createNewUser(user);
            System.out.println("Affect Row:"+affectRow);
            if(affectRow>0){
                return "create user successfully";
            }
            else return "cannot create a new user";
        }catch (Exception exception){
            return exception.getMessage();
        }

    }
    @GetMapping("/user_accounts")
    public Response<List<UserAccount>> getAllUserAccounts() {
        try {
            List<UserAccount> data = userService.getAllUserAccounts();
            return Response.<List<UserAccount>>ok().setPayload(data).setMessage("Successfully retrieved");

        } catch (Exception ex) {
            return Response.<List<UserAccount>>exception().setMessage("Exception occurs")
                    .setSuccess(false);
        }
    }
    @DeleteMapping("/remove-user/{id}")
    public Response<User> removeUserAccount(@PathVariable("id") int id){
        try {
            int deletedRow = userService.removeUserById(id);
            if (deletedRow > 0){
                return Response.<User>deleteSuccess().setMessage("Delete successes!");
            }else{
                return Response.<User>notFound().setMessage("not found!");
            }
        }catch (Exception e){
            System.out.println("Program got error: " + e.getMessage());
            return Response.<User>exception().setMessage("Exception occurs");
        }
    }
    @PutMapping ("/update-user/{id}")
    public Response<User> updateUserAccount(@RequestBody User user,
                                                         @PathVariable("id") int id){
        try {
            int updateRowEffected = userService.updateUserById(user, id);
            if (updateRowEffected > 0){
                user.setUserId(id);
                return Response.<User>updateSuccess().setMessage("Update Successfully!").setPayload(user);
            }else{
                return Response.<User>notFound().setMessage(" not found!");
            }
        }catch (Exception e){
            System.out.println("Error happened : " + e.getMessage());
            return Response.<User>exception().setMessage("Exception occurs");
        }
    }

}
