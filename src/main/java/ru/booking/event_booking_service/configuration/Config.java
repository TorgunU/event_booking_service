package ru.booking.event_booking_service.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@ComponentScan
@EnableWebSecurity
public class Config {
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
