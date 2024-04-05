package ru.booking.event_booking_service.responce;

public record UserResponse(
        Long id,
        String username,
        int age) {
}
