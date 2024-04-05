package ru.booking.event_booking_service.responce;

import jakarta.validation.constraints.NotEmpty;

public record JwtRequest(
        @NotEmpty
        String username,
        @NotEmpty
        String password
) {
}
