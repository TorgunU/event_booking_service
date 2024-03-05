package ru.booking.event_booking_service.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import ru.booking.event_booking_service.role.Role;

public record UserDTO(
        Long id,
        @NotEmpty
        String username,
        @Min(1) @Max(150) int age,
        @NotEmpty
        String password,
        @NotNull
        Role role) {
}
