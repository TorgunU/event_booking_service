package ru.booking.event_booking_service.service;

import ru.booking.event_booking_service.dto.UserRegistrationDTO;

public interface UserRegistrationService {
    void createNewUser(UserRegistrationDTO userRegistrationDTO);
}
