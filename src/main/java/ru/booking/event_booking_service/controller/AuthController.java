package ru.booking.event_booking_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import ru.booking.event_booking_service.buisnessEntity.User;
import ru.booking.event_booking_service.dto.UserDTO;
import ru.booking.event_booking_service.dto.UserRegistrationDTO;
import ru.booking.event_booking_service.mapper.UserMapper;
import ru.booking.event_booking_service.responce.JwtRequest;
import ru.booking.event_booking_service.responce.JwtResponse;
import ru.booking.event_booking_service.responce.MessageResponse;
import ru.booking.event_booking_service.service.RegistrationService;
import ru.booking.event_booking_service.service.UserRegistrationService;
import ru.booking.event_booking_service.service.UserService;
import ru.booking.event_booking_service.utils.ResponseUtils;

@RestController
@RequestMapping("/users")
public class AuthController {
    private final RegistrationService registrationService;
    private final UserRegistrationService userRegistrationService;
    private final UserService userService;
    private final UserMapper mapper;

    @Autowired
    public AuthController(RegistrationService registrationService,
                          UserRegistrationService userRegistrationService,
                          UserService userService,
                          UserMapper mapper) {
        this.registrationService = registrationService;
        this.userRegistrationService = userRegistrationService;
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping("/auth")
    public ResponseEntity<JwtResponse> createAuthToken(@RequestBody JwtRequest request)
            throws BadCredentialsException {
        String token = registrationService.createAuthToken(request);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping()
    public ResponseEntity<MessageResponse> createNewUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        userRegistrationService.createNewUser(userRegistrationDTO);
        return ResponseEntity.ok(ResponseUtils
                .getSuccessResponse(ResponseUtils.CREATION_MESSAGE,
                        userRegistrationDTO.username()));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<MessageResponse> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        UserDTO userDTO = mapper.mapFromUserToDto.apply(user);
        return ResponseEntity.ok(ResponseUtils
                .getSuccessResponse(ResponseUtils.CREATION_MESSAGE,
                        userDTO.toString()));
    }
}
