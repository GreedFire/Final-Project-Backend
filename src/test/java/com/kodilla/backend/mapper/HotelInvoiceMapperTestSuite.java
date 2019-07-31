package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.dto.hotel.HotelInvoiceDto;
import com.kodilla.backend.domain.entity.hotel.HotelInvoice;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelInvoiceMapperTestSuite {
    @Autowired
    private HotelInvoiceMapper mapper;

    @Test
    public void mapToEntityTest(){
        //Given
        LocalDate date = LocalDate.now();
        HotelInvoiceDto invoiceDto = new HotelInvoiceDto(date, new BigDecimal("10"), 1L, 2L);
        //When
        HotelInvoice invoice = mapper.mapToEntity(invoiceDto);
        //Then
        Assert.assertEquals(invoiceDto.getDate(), invoice.getDate());
        Assert.assertEquals(invoiceDto.getPrice(), invoice.getPrice());
        Assert.assertEquals(invoiceDto.getHotelId(), invoice.getHotelId());
    }
}
