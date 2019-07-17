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

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public boolean isUserExist(User user) {
        Optional<User> userResult = userRepo.findByUsername(user.getUsername());
        return userResult.isPresent();


    }
}
