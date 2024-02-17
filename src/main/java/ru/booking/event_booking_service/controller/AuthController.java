package ru.booking.event_booking_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.booking.event_booking_service.dto.UserRegistrationDTO;
import ru.booking.event_booking_service.responce.JwtRequest;
import ru.booking.event_booking_service.responce.JwtResponse;
import ru.booking.event_booking_service.responce.MessageResponse;
import ru.booking.event_booking_service.service.AuthService;
import ru.booking.event_booking_service.service.UserRegistrationService;
import ru.booking.event_booking_service.utils.ResponseUtils;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final UserRegistrationService userRegistrationService;

    @Autowired
    public AuthController(AuthService authService, UserRegistrationService userRegistrationService) {
        this.authService = authService;
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping()
    public ResponseEntity<JwtResponse> createAuthToken(@RequestBody JwtRequest request)
            throws BadCredentialsException {
        String token = authService.createAuthToken(request);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/registration")
    public ResponseEntity<MessageResponse> createNewUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        userRegistrationService.createNewUser(userRegistrationDTO);
        return ResponseEntity.ok(ResponseUtils
                .getSuccessResponse(ResponseUtils.CREATION_MESSAGE,
                        userRegistrationDTO.getUsername()));
    }
}
