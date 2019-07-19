package com.kodilla.backend.controller;

import com.kodilla.backend.domain.UserIdDto;
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
    public UserIdDto getId(@RequestParam String username, @RequestParam String password) {
            UserIdDto userIdDto = new UserIdDto();
            Optional<User> user = database.getId(username, password);
            if(user.isPresent()){
               userIdDto.setId(user.get().getId());
            }
            return userIdDto;
    }

    @PutMapping("/users/signIn")
    public void signIn(@RequestParam long userId){
        database.signIn(userId);
    }

    @PutMapping("/users/signOut")
    public void signOut(@RequestParam long userId){database.signOut(userId);}

}
