package ru.booking.event_booking_service.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import ru.booking.event_booking_service.buisnessEntity.User;
import ru.booking.event_booking_service.dto.UserRegistrationDTO;
import ru.booking.event_booking_service.responce.JwtRequest;
import ru.booking.event_booking_service.responce.JwtResponse;
import ru.booking.event_booking_service.service.RegistrationService;
import ru.booking.event_booking_service.service.UserRegistrationService;
import ru.booking.event_booking_service.service.UserService;

@RestController
@RequestMapping("/users")
public class AuthController {
    private final RegistrationService registrationService;
    private final UserRegistrationService userRegistrationService;
    private final UserService userService;

    @Autowired
    public AuthController(RegistrationService registrationService,
                          UserRegistrationService userRegistrationService,
                          UserService userService) {
        this.registrationService = registrationService;
        this.userRegistrationService = userRegistrationService;
        this.userService = userService;
    }

    @PostMapping("/auth")
    public ResponseEntity<JwtResponse> createAuthToken(@RequestBody JwtRequest request)
            throws BadCredentialsException {
        String token = registrationService.createAuthToken(request);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping()
    public ResponseEntity<User> createNewUser(@RequestBody @Valid UserRegistrationDTO userRegistrationDTO) {
        User user = userRegistrationService.createNewUser(userRegistrationDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
