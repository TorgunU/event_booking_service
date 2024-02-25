package ru.booking.event_booking_service.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserRegistrationDTO(
        @Size(min = 2, max = 50, message = "Username must be between 2 and 50 characters")
        @NotEmpty(message = "Username cannot be empty")
        String username,
        @Size(min = 8, max = 70, message = "Password must be between 8 and 70 characters")
        String password,
        @NotEmpty(message = "Password verification field cannot be empty")
        String verifyPassword) {
}
