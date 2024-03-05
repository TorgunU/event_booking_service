package ru.booking.event_booking_service.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import ru.booking.event_booking_service.controller.AuthController;
import ru.booking.event_booking_service.dto.ErrorMessageDTO;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class UserAccessDeniedHandler implements AccessDeniedHandler {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        logger.error("Access denied error", accessDeniedException);
        ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO(
                "Ошибка доступа",
                accessDeniedException.getMessage(),
                LocalDateTime.now()
        );
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter()
                        .write(objectMapper.writeValueAsString(errorMessageDTO));
    }
}
