package ru.booking.event_booking_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import ru.booking.event_booking_service.role.Role;

public record UserRegistrationDTO(
        Role role,
        @Size(min = 2, max = 50, message = "Username must be between 2 and 50 characters")
        @NotBlank(message = "Username cannot be empty")
        String username,
        @Size(min = 8, max = 70, message = "Password must be between 8 and 70 characters")
        String password,
        @NotBlank(message = "Password verification field cannot be empty")
        String verifyPassword) {
}
