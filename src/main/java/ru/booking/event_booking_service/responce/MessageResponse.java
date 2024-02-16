package ru.booking.event_booking_service.responce;

import java.time.LocalDate;

public class MessageResponse {
    private Integer status;
    private final LocalDate timeStamp =  LocalDate.now();
    private String message;
    private Object object;

    public MessageResponse(Integer status, String message, Object object) {
        this.status = status;
        this.message = message;
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return "MessageResponse{" +
                "status=" + status +
                ", timeStamp=" + timeStamp +
                ", message='" + message + '\'' +
                ", object=" + object +
                '}';
    }
}
