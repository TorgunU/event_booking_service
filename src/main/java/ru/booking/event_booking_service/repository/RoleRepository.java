package ru.booking.event_booking_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.booking.event_booking_service.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(String name);
}
