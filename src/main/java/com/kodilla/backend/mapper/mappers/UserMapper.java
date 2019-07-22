package com.kodilla.backend.mapper.mappers;

import com.kodilla.backend.domain.dto.UserDto;
import com.kodilla.backend.domain.entity.User;
import com.kodilla.backend.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper implements Mapper<User, UserDto> {

    @Override
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

    @Override
    public UserDto mapToDto(User user){
        return new UserDto(
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getFirstname(),
                user.getLastname(),
                user.getBirthdate(),
                user.isSignedIn()
        );
    }

    @Override
    public List<User> mapToEntityList(List<UserDto> list){
        return list.stream()
                .map(e -> new User(
                        e.getUsername(),
                        e.getPassword(),
                        e.getEmail(),
                        e.getFirstname(),
                        e.getLastname(),
                        e.getBirthdate(),
                        e.isSignedIn()
                )).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> mapToDtoList(List<User> list){
        return list.stream()
                .map(e -> new UserDto(
                        e.getUsername(),
                        e.getPassword(),
                        e.getEmail(),
                        e.getFirstname(),
                        e.getLastname(),
                        e.getBirthdate(),
                        e.isSignedIn()
                )).collect(Collectors.toList());
    }


}
