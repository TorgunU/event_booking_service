package ru.booking.event_booking_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.booking.event_booking_service.buisnessEntity.User;
import ru.booking.event_booking_service.entity.UserEntity;
import ru.booking.event_booking_service.mapper.UserMapper;
import ru.booking.event_booking_service.repository.UserRepository;
import ru.booking.event_booking_service.role.Role;

@Service
public class AdminService {
    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public AdminService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public User setAdminRoleById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new DataSourceLookupFailureException("User id is not contains in BD"));
        if (userEntity.getRole().name().equals(Role.ROLE_ADMIN.name())) {
            throw new IllegalArgumentException("User is already admin");
        }
        userEntity.setRole(Role.ROLE_ADMIN);
        userRepository.save(userEntity);
        return userMapper.mapFromEntityToUser.apply(userEntity);
    }
}
