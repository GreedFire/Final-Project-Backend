package com.kodilla.backend.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kodilla.backend.LocalDateAdapter;
import com.kodilla.backend.domain.dto.UserDto;
import com.kodilla.backend.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @Test
    public void createUser() throws Exception {
        //Given
        LocalDate date = LocalDate.now();
        UserDto userDto = new UserDto("username", "password", "email", "firstname", "lastname", date);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter().nullSafe()).create();
        String json = gson.toJson(userDto);
        when(service.createUser(userDto)).thenReturn(true);
        //When & then
        mockMvc.perform(post("/v1/users").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(true)));
    }

    @Test
    public void getId() throws Exception {
        //Given
        when(service.getId("username", "password")).thenReturn(1L);
        //When & then
        mockMvc.perform(get("/v1/users/getId").contentType(MediaType.APPLICATION_JSON)
                .param("username", "username")
                .param("password", "password"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(1)));
    }

    @Test
    public void checkIfLoggedIn() throws Exception {
        //Given
        when(service.checkIfLoggedIn(1L)).thenReturn(true);
        //When & then
        mockMvc.perform(get("/v1/users/loggedIn").contentType(MediaType.APPLICATION_JSON)
                .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(true)));
    }

    @Test
    public void signIn() throws Exception {
        //Given
        //When & then
        mockMvc.perform(put("/v1/users/signIn").contentType(MediaType.APPLICATION_JSON)
                .param("userId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void signOut() throws Exception {
        //Given
        //When & then
        mockMvc.perform(put("/v1/users/signOut").contentType(MediaType.APPLICATION_JSON)
                .param("userId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void changePassword() throws Exception {
        //Given
        //When & then
        mockMvc.perform(put("/v1/users/passwordChange").contentType(MediaType.APPLICATION_JSON)
                .param("id", "1")
                .param("oldPassword", "xxx")
                .param("newPassword", "yyy"))
                .andExpect(status().isOk());
    }

    @Test
    public void isNewPasswordOk() throws Exception {
        //Given
        when(service.isNewPasswordOk(1, "yyy")).thenReturn(true);
        //When & then
        mockMvc.perform(get("/v1/users/checkNewPassword").contentType(MediaType.APPLICATION_JSON)
                .param("id", "1")
                .param("newPassword", "yyy"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(true)));
    }

    @Test
    public void deleteAccount() throws Exception {
        //Given
        //When & then
        mockMvc.perform(delete("/v1/users").contentType(MediaType.APPLICATION_JSON)
                .param("id", "1")
                .param("password", "yyy"))
                .andExpect(status().isOk());
    }
}