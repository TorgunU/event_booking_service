package ru.booking.event_booking_service.mapper;

import org.springframework.stereotype.Component;
import ru.booking.event_booking_service.buisnessEntity.Location;
import ru.booking.event_booking_service.dto.LocationDTO;
import ru.booking.event_booking_service.entity.LocationEntity;

import java.util.function.Function;

@Component
public class LocationMapper {
    public final Function<LocationDTO, Location> fromDtoToLocation = dto -> new Location(
            dto.id(),
            dto.address(),
            dto.name(),
            dto.description(),
            dto.capacity()
    );

    public final Function<Location, LocationDTO> fromLocationToDto = location -> new LocationDTO(
            location.id(),
            location.address(),
            location.name(),
            location.description(),
            location.capacity()
    );

    public final Function<LocationEntity, Location> fromEntityToLocation = entity -> new Location(
            entity.getId(),
            entity.getAddress(),
            entity.getName(),
            entity.getDescription(),
            entity.getCapacity()
    );

    public final Function<Location, LocationEntity> fromLocationToEntity = location -> new LocationEntity(
            location.address(),
            location.name(),
            location.description(),
            location.capacity()
    );
}
