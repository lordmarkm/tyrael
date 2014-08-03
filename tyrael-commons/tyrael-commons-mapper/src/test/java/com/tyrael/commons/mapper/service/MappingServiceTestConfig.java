package com.tyrael.commons.mapper.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappingServiceTestConfig {

    @Bean
    public EntityMapperService entityMapperService() {
        return new EntityMapperService();
    }
}
