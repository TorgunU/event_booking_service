package ru.booking.event_booking_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.booking.event_booking_service.buisnessEntity.User;
import ru.booking.event_booking_service.role.Role;
import ru.booking.event_booking_service.service.UserService;

@Component
public class AdminAccountInitializer implements CommandLineRunner {
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AdminAccountInitializer(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        String username = "admin";
        if (userService.isUserExist(username)) {
            return;
        }
        String password = "admin1";
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(
                1L,
                username,
                encodedPassword,
                Role.ROLE_ADMIN
        );
        userService.saveUser(user);
    }
}
