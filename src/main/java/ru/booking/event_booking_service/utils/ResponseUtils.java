package ru.booking.event_booking_service.utils;

import org.springframework.http.HttpStatus;
import ru.booking.event_booking_service.responce.MessageResponse;
public class ResponseUtils {

    public static final String CREATION_MESSAGE = "The %s have been successful created";

    public static MessageResponse getSuccessResponse(String message, String className) {
        return new MessageResponse(HttpStatus.OK.value(), String.format(message, className.toLowerCase()), className);
    }
}
