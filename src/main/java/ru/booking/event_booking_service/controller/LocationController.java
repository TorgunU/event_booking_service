package ru.booking.event_booking_service.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.booking.event_booking_service.buisnessEntity.Location;
import ru.booking.event_booking_service.dto.LocationDTO;
import ru.booking.event_booking_service.mapper.LocationMapper;
import ru.booking.event_booking_service.service.LocationService;

import java.util.List;

@RestController("/locations")
public class LocationController {
    private LocationService locationService;
    private LocationMapper mapper;

    @Autowired
    public LocationController(LocationService locationService, LocationMapper mapper) {
        this.locationService = locationService;
        this.mapper = mapper;
    }

    @GetMapping()
    public ResponseEntity<List<LocationDTO>> getAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        List<LocationDTO> locationsDTO = locations.stream()
                .map(mapper.fromLocationToDto)
                .toList();
        return new ResponseEntity<>(locationsDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<LocationDTO> addLocation(@RequestBody @Valid LocationDTO locationDTO) {
        Location location = mapper.fromDtoToLocation.apply(locationDTO);
        Location addedLocation  = locationService.addLocation(location);
        LocationDTO addedDto = mapper.fromLocationToDto.apply(addedLocation);
        return new ResponseEntity<>(addedDto, HttpStatus.OK);
    }

    @DeleteMapping("/{locationID}")
    public ResponseEntity<LocationDTO> deleteLocation(@PathVariable Long locationID) {
        Location deletedLocation = locationService.deleteLocation(locationID);
        LocationDTO locationDTO = mapper.fromLocationToDto.apply(deletedLocation);
        return new ResponseEntity<>(locationDTO, HttpStatus.OK);
    }

    @GetMapping("/{locationID}")
    public ResponseEntity<LocationDTO> getLocationById(@PathVariable Long locationID) {
        Location location = locationService.getLocationById(locationID);
        LocationDTO locationDTO = mapper.fromLocationToDto.apply(location);
        return new ResponseEntity<>(locationDTO, HttpStatus.OK);
    }

    @PutMapping("/{locationID}")
    public ResponseEntity<LocationDTO> updateLocation(@PathVariable Long locationID,
                                               @RequestBody @Valid LocationDTO locationDTO) {
        Location location = mapper.fromDtoToLocation.apply(locationDTO);
        Location updatedLocation = locationService.updateLocationById(locationID, location);
        LocationDTO updatedDTO = mapper.fromLocationToDto.apply(updatedLocation);
        return new ResponseEntity<>(updatedDTO, HttpStatus.OK);
    }
}
