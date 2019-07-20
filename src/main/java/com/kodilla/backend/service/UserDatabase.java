package com.kodilla.backend.service;

import com.kodilla.backend.domain.entity.User;
import com.kodilla.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDatabase {

    @Autowired
    private UserRepo userRepo;

    public void createUser(User user) {
        userRepo.save(user);
    }

    public boolean isUserExist(User user) {
        Optional<User> userResult = userRepo.findByUsername(user.getUsername());
        return userResult.isPresent();
    }

    public Optional<User> getUserId(String username, String password){
        return userRepo.findByUsernameAndPassword(username, password);
    }

    public void signIn(long id){
        userRepo.signIn(id);
    }

    public void signOut(long id){
        userRepo.signOut(id);
    }

    public Optional<User> getById(long id){
        return userRepo.findById(id);
    }

    public void updatePassword(long id, String newPassword){
        userRepo.updatePassword(id, newPassword);
    }

    public Optional<User> checkOldPassword(long id, String oldPassword){
        return userRepo.checkOldPassword(id, oldPassword);
    }

    public void deleteUser(long id, String password){
        userRepo.deleteUser(id, password);
    }

}
