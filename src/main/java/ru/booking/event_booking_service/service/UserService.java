package ru.booking.event_booking_service.service;

import ru.booking.event_booking_service.model.User;

import java.util.List;

public interface UserService {
    User getUserByUsername(String username);
    User getUserById(Long id);
    void saveUser(User user);
    boolean isUserExist(String username);
    List<User> getAllUsers();
}
