package ru.booking.event_booking_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.booking.event_booking_service.dto.LocationDTO;
import ru.booking.event_booking_service.service.LocationService;

import java.util.List;

@RestController
public class LocationController {

    private LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/locations")
    public ResponseEntity<List<LocationDTO>> getAllLocations() {
        List<LocationDTO> locations = locationService.getAllLocations();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @PostMapping("/locations")
    public ResponseEntity<Void> addLocation(@RequestBody LocationDTO locationDTO) {
        locationService.addLocation(locationDTO);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/locations/{locationID}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long locationID) {
        locationService.deleteLocation(locationID);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("locations/{locationID}")
    public ResponseEntity<LocationDTO> getLocationById(@PathVariable Long locationID) {
        LocationDTO location = locationService.getLocationById(locationID);
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @PutMapping("locations/{locationID}")
    public ResponseEntity<Void> updateLocation(@PathVariable Long locationID,
                                               @RequestBody LocationDTO locationDTO) {
        locationService.updateLocationById(locationID, locationDTO);
        return ResponseEntity.accepted().build();
    }

}
