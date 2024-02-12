package ru.booking.event_booking_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.booking.event_booking_service.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}
