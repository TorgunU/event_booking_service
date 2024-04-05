package ru.booking.event_booking_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.booking.event_booking_service.buisnessEntity.User;
import ru.booking.event_booking_service.dto.UserRegistrationDTO;
import ru.booking.event_booking_service.role.Role;

@Service
public class UserRegistrationService {
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserRegistrationService(UserService userService,
                                   PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User createNewUser(UserRegistrationDTO userDTO) {
        if (userService.isUserExist(userDTO.username())) {
            throw new IllegalArgumentException("This login is already contains!");
        }
        String encodedPassword = passwordEncoder.encode(userDTO.password());
        User user = new User(
                null,
                userDTO.username(),
                userDTO.age(),
                encodedPassword,
                Role.ROLE_USER
        );
        return userService.saveUser(user);
    }
}
