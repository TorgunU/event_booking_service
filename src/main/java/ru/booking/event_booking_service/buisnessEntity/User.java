package ru.booking.event_booking_service.buisnessEntity;

import ru.booking.event_booking_service.role.Role;

public record User(
        Long id,
        String username,
        String password,
        Role role) {

}
