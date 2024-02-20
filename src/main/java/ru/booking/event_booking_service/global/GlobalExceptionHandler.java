package ru.booking.event_booking_service.global;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.booking.event_booking_service.dto.MessageErrorDTO;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { EntityNotFoundException.class })
    protected ResponseEntity<Object> handleEntityNotFound(Exception exception) {
        var message = new MessageErrorDTO(
                "Сущность не найдена",
                exception.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(message);
    }

    @ExceptionHandler(value = {
            ConstraintViolationException.class,
            MethodArgumentNotValidException.class
    })
    protected ResponseEntity<Object> handleConstraintViolationException(Exception exception) {
        var message = new MessageErrorDTO(
                "Ошибка валидации запроса",
                exception.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(message);
    }

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Object> handleInternalServerError(Exception exception) {
        var message = new MessageErrorDTO(
                "Внутренняя ошибка сервера",
                exception.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(message);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> handleException(Exception exception) {
        var message = new MessageErrorDTO(
                "Недопустимое значение",
                exception.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(message);
    }
}
