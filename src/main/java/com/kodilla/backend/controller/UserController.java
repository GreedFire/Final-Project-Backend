package com.kodilla.backend.controller;

import com.kodilla.backend.domain.dto.UserDto;
import com.kodilla.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Boolean createUser(@RequestBody UserDto userDto) {
        return service.createUser(userDto);
    }

    @GetMapping("/getId")
    public Long getId(@RequestParam String username, @RequestParam String password) {
        return service.getId(username, password);
    }

    @GetMapping("/loggedIn")
    public Boolean checkIfLoggedIn(@RequestParam long id) {
        return service.checkIfLoggedIn(id);
    }

    @PutMapping("/signIn")
    public void signIn(@RequestParam long userId) {
        service.signIn(userId);
    }

    @PutMapping("/signOut")
    public void signOut(@RequestParam long userId) {
        service.signOut(userId);
    }

    @PutMapping("/passwordChange")
    public void changePassword(@RequestParam long id, @RequestParam String oldPassword, @RequestParam String newPassword) {
        service.changePassword(id, oldPassword, newPassword);
    }

    @GetMapping("/checkNewPassword")
    public Boolean isNewPasswordOk(@RequestParam long id, @RequestParam String newPassword) {
        return service.isNewPasswordOk(id, newPassword);
    }

    @DeleteMapping
    public void deleteAccount(@RequestParam long id, @RequestParam String password) {
        service.deleteAccount(id, password);
    }


}
