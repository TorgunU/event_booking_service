package ru.booking.event_booking_service.buisnessEntity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record Location(
        @NotNull Long id,
        @NotEmpty @NotNull String address,
        @NotEmpty @NotNull String name,
        String description,
        @Min(value = 1) int capacity) {
}
