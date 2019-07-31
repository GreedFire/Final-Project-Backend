package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.dto.UserDto;
import com.kodilla.backend.domain.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTestSuite {

    @Autowired
    private UserMapper mapper;

    @Test
    public void mapToEntityTest(){
        //Given
        LocalDate date = LocalDate.now();
        UserDto userDto = new UserDto( "GreedFire", "xxx", "x@x.com", "Dawid", "Majchrzak", date, false);
        //When
        User user = mapper.mapToEntity(userDto);
        //Then
        Assert.assertEquals("GreedFire", user.getUsername());
        Assert.assertEquals("x@x.com", user.getEmail());
        Assert.assertEquals(date, user.getBirthdate());
        Assert.assertFalse(user.isSignedIn());
    }
}
