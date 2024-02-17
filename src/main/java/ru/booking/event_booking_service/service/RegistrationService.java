package ru.booking.event_booking_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.booking.event_booking_service.responce.JwtRequest;
import ru.booking.event_booking_service.token.JwtTokenManager;

@Service
public class RegistrationService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtTokenManager jwtTokenManager;

    @Autowired
    public RegistrationService(AuthenticationManager authenticationManager,
                               UserDetailsService userDetailsService,
                               JwtTokenManager jwtTokenManager) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenManager = jwtTokenManager;
    }

    public String createAuthToken(@RequestBody JwtRequest request)
            throws BadCredentialsException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        return jwtTokenManager.generateToken(userDetails);
    }
}
