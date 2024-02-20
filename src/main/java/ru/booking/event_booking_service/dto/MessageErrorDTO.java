package ru.booking.event_booking_service.dto;

import java.time.LocalDateTime;

public class MessageErrorDTO {
    private final String message;
    private final String detailedMessage;
    private final LocalDateTime localDateTime;

    public MessageErrorDTO(String message, String detailedMessage, LocalDateTime localDateTime) {
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
