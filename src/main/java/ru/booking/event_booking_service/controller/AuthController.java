package ru.booking.event_booking_service.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import ru.booking.event_booking_service.buisnessEntity.User;
import ru.booking.event_booking_service.dto.UserRegistrationDTO;
import ru.booking.event_booking_service.mapper.UserResponseMapper;
import ru.booking.event_booking_service.responce.JwtRequest;
import ru.booking.event_booking_service.responce.JwtResponse;
import ru.booking.event_booking_service.responce.UserResponse;
import ru.booking.event_booking_service.service.AuthorizationService;
import ru.booking.event_booking_service.service.UserRegistrationService;
import ru.booking.event_booking_service.service.UserService;

@RestController
@RequestMapping("/users")
public class AuthController {
    private final AuthorizationService authorizationService;
    private final UserRegistrationService userRegistrationService;
    private final UserService userService;
    private final UserResponseMapper userResponseMapper;

    @Autowired
    public AuthController(AuthorizationService authorizationService,
                          UserRegistrationService userRegistrationService,
                          UserService userService, UserResponseMapper userResponseMapper) {
        this.authorizationService = authorizationService;
        this.userRegistrationService = userRegistrationService;
        this.userService = userService;
        this.userResponseMapper = userResponseMapper;
    }

    @PostMapping("/auth")
    public ResponseEntity<JwtResponse> createAuthToken(@RequestBody JwtRequest request)
            throws BadCredentialsException {
        String token = authorizationService.createAuthToken(request);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping()
    public ResponseEntity<UserResponse> createNewUser(@RequestBody @Valid UserRegistrationDTO userRegistrationDTO) {
        User user = userRegistrationService.createNewUser(userRegistrationDTO);
        UserResponse response = userResponseMapper.mapFromUserToResponse.apply(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        UserResponse response = userResponseMapper.mapFromUserToResponse.apply(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
