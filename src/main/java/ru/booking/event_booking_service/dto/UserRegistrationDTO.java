package ru.booking.event_booking_service.dto;

import jakarta.validation.constraints.*;

public record UserRegistrationDTO(
        @Size(min = 2, max = 50, message = "Username must be between 2 and 50 characters")
        @NotBlank(message = "Username cannot be empty")
        String username,
        @NotNull
        @Min(value = 0, message = "Age must be greater than or equal to 0")
        @Max(value = 150, message = "Age must be less than or equal to 127")
        Integer age,
        @Size(min = 8, max = 70, message = "Password must be between 8 and 70 characters")
        String password,
        @NotBlank(message = "Password verification field cannot be empty")
        String verifyPassword) {
}
