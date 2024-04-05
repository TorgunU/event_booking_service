package ru.booking.event_booking_service.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import ru.booking.event_booking_service.controller.AuthController;
import ru.booking.event_booking_service.dto.ErrorMessageDTO;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public UserAuthenticationEntryPoint() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        logger.error("Authentication error", authException);
        var messageError = new ErrorMessageDTO(
                "Authentication error",
                authException.getMessage(),
                LocalDateTime.now()
        );
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter()
                        .write(objectMapper.writeValueAsString(messageError));
    }
}
