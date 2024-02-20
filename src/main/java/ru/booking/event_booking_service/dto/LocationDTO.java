package ru.booking.event_booking_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LocationDTO(
        @Min(value = 1) Long id,
        @NotBlank String address,
        @NotBlank String name,
        String description,
        @Min(value = 1) int capacity) {
}