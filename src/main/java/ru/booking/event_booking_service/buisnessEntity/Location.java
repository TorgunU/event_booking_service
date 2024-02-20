package ru.booking.event_booking_service.buisnessEntity;

public record Location(
        Long id,
        String address,
        String name,
        String description,
        int capacity) {
}
