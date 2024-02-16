package ru.booking.event_booking_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.booking.event_booking_service.dto.JwtRequest;
import ru.booking.event_booking_service.dto.JwtResponse;
import ru.booking.event_booking_service.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<JwtResponse> createAuthToken(@RequestBody JwtRequest request)
            throws BadCredentialsException {
        String token = authService.createAuthToken(request);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
