package ru.booking.event_booking_service.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class ErrorMessageDTO {
    @NotBlank
    private final String message;
    @NotBlank
    private final String detailedMessage;
    @NotBlank
    private final LocalDateTime localDateTime;

    public ErrorMessageDTO(String message, String detailedMessage, LocalDateTime localDateTime) {
        this.message = message;
        this.detailedMessage = detailedMessage;
        this.localDateTime = localDateTime;
    }

    public String getMessage() {
        return message;
    }

    public String getDetailedMessage() {
        return detailedMessage;
    }

    public LocalDateTime getTime() {
        return localDateTime;
    }
}
