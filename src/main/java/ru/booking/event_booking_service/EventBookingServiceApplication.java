package ru.booking.event_booking_service;

import org.aspectj.lang.annotation.Before;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EventBookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventBookingServiceApplication.class, args);
	}
}
