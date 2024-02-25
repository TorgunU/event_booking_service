package ru.booking.event_booking_service.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import ru.booking.event_booking_service.role.Role;

public record UserDTO(
        Long id,
        @NotEmpty @NotNull String username,
        @NotEmpty @NotNull String password,
        @NotEmpty @NotNull Role role) {
}
