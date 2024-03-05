package ru.booking.event_booking_service.mapper;

import org.springframework.stereotype.Component;
import ru.booking.event_booking_service.buisnessEntity.User;
import ru.booking.event_booking_service.dto.UserDTO;
import ru.booking.event_booking_service.entity.UserEntity;

import java.util.function.Function;

@Component
public class UserMapper {

    public final Function<UserDTO, User> mapFromDtoToUser = dto -> new User(
            dto.id(),
            dto.username(),
            dto.age(),
            dto.password(),
            dto.role()
    );

    public final Function<User, UserDTO> mapFromUserToDto = user -> new UserDTO(
            user.id(),
            user.username(),
            user.age(),
            user.passwordHash(),
            user.role()
    );

    public final Function<User, UserEntity> mapFromUserToEntity = user -> new UserEntity(
            user.id(),
            user.username(),
            user.age(),
            user.passwordHash(),
            user.role()
    );

    public final Function<UserEntity, User> mapFromEntityToUser = userEntity -> new User(
            userEntity.getId(),
            userEntity.getUsername(),
            userEntity.getAge(),
            userEntity.getPasswordHash(),
            userEntity.getRole()
    );
}
