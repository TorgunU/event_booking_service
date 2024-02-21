package ru.booking.event_booking_service.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.booking.event_booking_service.buisnessEntity.Location;
import ru.booking.event_booking_service.entity.LocationEntity;
import ru.booking.event_booking_service.mapper.LocationMapper;
import ru.booking.event_booking_service.repository.LocationRepository;

import java.util.List;

@Service
public class LocationService {
    private LocationRepository repository;
    private LocationMapper mapper;

    @Autowired
    public LocationService(LocationRepository repository, LocationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<Location> getAllLocations() {
        return repository.findAll().stream()
                .map(mapper.fromEntityToLocation::apply)
                .toList();
    }

    @Transactional
    public Location addLocation(Location location) {
        LocationEntity locationEntity = mapper.fromLocationToEntity.apply(location);
        repository.save(locationEntity);
        return mapper.fromEntityToLocation.apply(locationEntity);
    }

    @Transactional
    public Location deleteLocation(Long id) {
        LocationEntity locationEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find location. Invalid id: " + id));
        repository.deleteById(id);
        return mapper.fromEntityToLocation.apply(locationEntity);
    }

    @Transactional(readOnly = true)
    public Location getLocationById(Long id) {
        LocationEntity locationEntity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Location id wasn't founded: " + id));
        return mapper.fromEntityToLocation.apply(locationEntity);
    }

    @Transactional
    public Location updateLocationById(Long id, Location location) {
        if (!location.id().equals(id)) {
            throw new IllegalArgumentException("Id of request body (" + location.id() +
                    ") doesn't match with required id (" + id + ").");
        } else if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Can't find location. Invalid id: " + id);
        }
        LocationEntity locationEntity = mapper.fromLocationToEntity.apply(location);
        locationEntity.setId(id);
        repository.save(locationEntity);
        return mapper.fromEntityToLocation.apply(locationEntity);
    }
}
