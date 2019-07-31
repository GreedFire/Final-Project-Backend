package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.dto.flight.FlightInvoiceDto;
import com.kodilla.backend.domain.entity.flight.FlightInvoice;
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
public class FlightInvoiceMapperTestSuite {

    @Autowired
    private FlightInvoiceMapper mapper;

    @Test
    public void mapToEntityTest() {
        //Given
        LocalDate date = LocalDate.now();
        FlightInvoiceDto invoiceDto = new FlightInvoiceDto( date, new BigDecimal("10"), 2L, 3L);
        //When
        FlightInvoice invoice = mapper.mapToEntity(invoiceDto);
        //Then
        Assert.assertEquals(invoiceDto.getUserId(), invoice.getUserId());
        Assert.assertEquals(invoiceDto.getDate(), invoice.getDate());
        Assert.assertEquals(invoiceDto.getPrice(), invoice.getPrice());
    }
}
