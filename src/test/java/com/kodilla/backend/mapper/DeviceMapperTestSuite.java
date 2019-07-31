package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.dto.DeviceDto;
import com.kodilla.backend.domain.entity.Device;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceMapperTestSuite {

    @Autowired
    private DeviceMapper mapper;

    @Test
    public void mapToEntityTest(){
        //Given
        String systemipaddress = null;
        InetAddress inetAddress = null;
        try {
            URL url_name = new URL("http://bot.whatismyipaddress.com");
            BufferedReader sc =
                    new BufferedReader(new InputStreamReader(url_name.openStream()));
            systemipaddress = sc.readLine().trim();
            inetAddress = InetAddress.getLocalHost();
        } catch (IOException e){
            System.out.println("Cannot read ip adress");
        }
        DeviceDto deviceDto = new DeviceDto(systemipaddress, inetAddress.toString(), System.getProperty("os.name"));
        //When
        Device device = mapper.mapToEntity(deviceDto);
        //Then
        Assert.assertEquals(systemipaddress, device.getIpAddress());
        Assert.assertEquals(inetAddress.toString(), device.getHostName());
        Assert.assertEquals(systemipaddress, device.getIpAddress());
    }
}
