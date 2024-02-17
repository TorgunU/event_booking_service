package ru.booking.event_booking_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.booking.event_booking_service.model.Role;
import ru.booking.event_booking_service.model.User;
import ru.booking.event_booking_service.service.RoleService;
import ru.booking.event_booking_service.service.UserService;

@Component
public class AdminAccountInitializer implements CommandLineRunner {
    private UserService userService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AdminAccountInitializer(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        String username = "admin";
        if (userService.isUserExist(username)) {
            return;
        }
        String password = "admin1";
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        Role role = roleService.getRoleByName("ROLE_ADMIN");
        user.setRole(role);
        userService.saveUser(user);
    }
}
