package ru.booking.event_booking_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LocationDTO(
        Long id,
        @NotBlank String address,
        @NotBlank String name,
        String description,
        @Min(value = 1) int capacity) {
}