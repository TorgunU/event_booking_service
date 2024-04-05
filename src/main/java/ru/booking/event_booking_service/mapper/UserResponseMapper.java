package ru.booking.event_booking_service.mapper;

import org.springframework.stereotype.Component;
import ru.booking.event_booking_service.buisnessEntity.User;
import ru.booking.event_booking_service.responce.UserResponse;

import java.util.function.Function;

@Component
public class UserResponseMapper {
    public final Function<User, UserResponse> mapFromUserToResponse = user -> new UserResponse(
            user.id(),
            user.username(),
            user.age()
    );
}
