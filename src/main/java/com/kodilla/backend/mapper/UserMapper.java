package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.dto.UserDto;
import com.kodilla.backend.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapToEntity(UserDto userDto) {
        return new User(
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getEmail(),
                userDto.getFirstname(),
                userDto.getLastname(),
                userDto.getBirthdate(),
                userDto.isSignedIn()
        );
    }

}
