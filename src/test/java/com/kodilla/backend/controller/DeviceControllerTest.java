package com.kodilla.backend.controller;


import com.google.gson.Gson;
import com.kodilla.backend.domain.dto.DeviceDto;
import com.kodilla.backend.service.DeviceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DeviceController.class)
public class DeviceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeviceService service;

    @Test
    public void saveDeviceInfo() throws Exception {
        //Given
        DeviceDto deviceDto = new DeviceDto("xxx", "yyy", "zzz");
        Gson gson = new Gson();
        String json = gson.toJson(deviceDto);
        //When & then
        mockMvc.perform(post("/v1/devices")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json))
                .andExpect(status().isOk());
    }
}