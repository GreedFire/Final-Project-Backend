package com.kodilla.backend.controller;

import com.kodilla.backend.domain.dto.UserDto;
import com.kodilla.backend.domain.entity.User;
import com.kodilla.backend.mapper.UserMapper;
import com.kodilla.backend.service.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
public class UserController {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserDatabase database;

    @PostMapping(path = "/users", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Boolean createUser(@RequestBody UserDto user) {
        if (!database.isUserExist(mapper.mapToUser(user))) {
            database.createUser(mapper.mapToUser(user));
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/users/getId")
    public Long getId(@RequestParam String username, @RequestParam String password) {
            Long id = null;
            Optional<User> user = database.getUserId(username, password);
            if(user.isPresent()){
               id = user.get().getId();
            }
            return id;
    }

    @GetMapping("/users/loggedIn")
    public Boolean checkIfLoggedIn(@RequestParam long id){
        boolean result = false;
        Optional<User> user = database.findById(id);
        if(user.isPresent()){
            result = user.get().isSignedIn();
        }
        return result;
    }

    @PutMapping("/users/signIn")
    public void signIn(@RequestParam long userId){
        database.signIn(userId);
    }

    @PutMapping("/users/signOut")
    public void signOut(@RequestParam long userId){database.signOut(userId);}

    @PutMapping("/users/passwordChange")
    public Boolean changePassword(@RequestParam long id, @RequestParam String oldPassword, @RequestParam String newPassword){
        boolean result = false;
        Optional<User> user = database.checkOldPassword(id, oldPassword);
        if(user.isPresent()){
            database.updatePassword(id, newPassword);
            result = true;
        }
        return result;
    }

    @DeleteMapping("users/delete")
    public Boolean deleteAccount(@RequestParam long id, @RequestParam String password){
        boolean result = false;
        Optional<User> user = database.checkOldPassword(id, password);
        if(user.isPresent()){
            database.deleteUser(id, password);
            result = true;
        }
        return result;
    }

}
