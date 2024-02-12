package ru.booking.event_booking_service.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.booking.event_booking_service.dto.LocationDTO;
import ru.booking.event_booking_service.entity.Location;
import ru.booking.event_booking_service.repository.LocationRepository;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    private LocationRepository repository;
    private ModelMapper modelMapper;

    @Autowired
    public LocationServiceImpl(LocationRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<LocationDTO> getAllLocations() {
        return repository.findAll().stream()
                .map(location -> modelMapper.map(location, LocationDTO.class))
                .toList();
    }

    @Override
    public void addLocation(LocationDTO locationDTO) {
        Location location = modelMapper.map(locationDTO, Location.class);
        repository.save(location);
    }

    @Override
    public void deleteLocation(Long locationID) {
        repository.deleteById(locationID);
    }

    @Override
    public LocationDTO getLocationById(Long id) {
        Location location = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Location id wasn't founded: " + id));
        return modelMapper.map(location, LocationDTO.class);
    }

    @Override
    public void updateLocationById(Long id, LocationDTO locationDTO) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Location id wasn't founded: " + id);
        }
        Location mappedLocation = modelMapper.map(locationDTO, Location.class);
        repository.save(mappedLocation);
    }
}
