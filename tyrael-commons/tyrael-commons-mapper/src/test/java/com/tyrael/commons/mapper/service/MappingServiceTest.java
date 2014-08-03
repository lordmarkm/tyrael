package com.tyrael.commons.mapper.service;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tyrael.commons.mapper.config.MapperConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MapperConfig.class, MappingServiceTestConfig.class})
public class MappingServiceTest {

    @Autowired
    private EntityMapperService service;

    @Test
    public void testMapping() {
        String name = "Coke 1.5";
        BigDecimal amount = new BigDecimal(50);

        EntityClass e = new EntityClass();
        e.setName(name);
        e.setAmount(amount);

        DtoClass d = service.toDto(e);
        assertEquals(name, d.getName());
        assertEquals(amount, d.getAmount());
    }
}
