package com.kodilla.backend.service;

import com.kodilla.backend.domain.dto.UserDto;
import com.kodilla.backend.domain.entity.User;
import com.kodilla.backend.mapper.Mapper;
import com.kodilla.backend.mapper.MapperFactory;
import com.kodilla.backend.mapper.mappers.UserMapper;
import com.kodilla.backend.service.database.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserDatabase database;
    private Mapper<User, UserDto> mapper = (UserMapper) MapperFactory.getInstance().getMapper(User.class);

    public Boolean createUser(UserDto userDto) {
        User user = mapper.mapToEntity(userDto);
        if (!database.isUserExist(user)) {
            database.createUser(user);
            return true;
        } else {
            return false;
        }
    }

    public Long getId(String username, String password) {
        Long id = null;
        Optional<User> user = database.getUserId(username, password);
        if (user.isPresent()) {
            id = user.get().getId();
        }
        return id;
    }

    public Boolean checkIfLoggedIn(long id) {
        boolean result = false;
        Optional<User> user = database.getById(id);
        if (user.isPresent()) {
            result = user.get().isSignedIn();
        }
        return result;
    }

    public void signIn(long userId) {
        database.signIn(userId);
    }

    public void signOut(long userId) {
        database.signOut(userId);
    }

    public void changePassword(long id, String oldPassword, String newPassword) {
        Optional<User> user = database.checkOldPassword(id, oldPassword);
        if (user.isPresent()) {
            database.updatePassword(id, newPassword);
        }
    }

    public Boolean isNewPasswordOk(long id, String newPassword) {
        boolean result = false;
        Optional<User> user = database.getById(id);
        if (user.isPresent() && user.get().getPassword().equals(newPassword)) {
            result = true;
        }
        return result;
    }

    public void deleteAccount(long id, String password) {
        Optional<User> user = database.checkOldPassword(id, password);
        if (user.isPresent()) {
            database.deleteUser(id, password);
        }
    }
}
