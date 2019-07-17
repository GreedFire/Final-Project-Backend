package com.kodilla.backend.controller;

import com.kodilla.backend.domain.dto.UserDto;
import com.kodilla.backend.mapper.UserMapper;
import com.kodilla.backend.service.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/v1")
public class UserController {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserDatabase database;

    @PostMapping(path = "/users", consumes = APPLICATION_JSON_VALUE)
    public boolean createUser(@RequestBody UserDto user) {
        if (!database.isUserExist(mapper.mapToUser(user))) {
            database.createUser(mapper.mapToUser(user));
            return true;
        } else return false;
    }
}
