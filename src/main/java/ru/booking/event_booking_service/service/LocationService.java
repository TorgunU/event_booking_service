package ru.booking.event_booking_service.service;

import ru.booking.event_booking_service.dto.LocationDTO;

import java.util.List;

public interface LocationService {
    List<LocationDTO> getAllLocations();
    void addLocation(LocationDTO locationDTO);
    void deleteLocation(Long locationID);
    LocationDTO getLocationById(Long id);
    void updateLocationById(Long id, LocationDTO locationDTO);
}
