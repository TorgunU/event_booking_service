package ru.booking.event_booking_service.responce;

import jakarta.validation.constraints.NotEmpty;

public record JwtResponse(
        @NotEmpty
        String token) {
}
