package ru.booking.event_booking_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.booking.event_booking_service.buisnessEntity.User;
import ru.booking.event_booking_service.entity.UserEntity;
import ru.booking.event_booking_service.mapper.UserMapper;
import ru.booking.event_booking_service.repository.UserRepository;

@Service
public class UserService {
    private UserRepository repository;
    private UserMapper userMapper;

    @Autowired
    public UserService(UserRepository repository, UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }

    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        UserEntity userEntity = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User wasn't founded by username: "
                        + username));
        return userMapper.mapFromEntityToUser.apply(userEntity);
    }

    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        UserEntity userEntity = repository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User wasn't founded by id: " +
                        id));
        return userMapper.mapFromEntityToUser.apply(userEntity);
    }

    @Transactional
    public User saveUser(User user) {
        UserEntity userEntity = userMapper.mapFromUserToEntity.apply(user);
        repository.save(userEntity);
        return userMapper.mapFromEntityToUser.apply(userEntity);
    }

    @Transactional(readOnly = true)
    public boolean isUserExist(String username) {
        return repository.existsByUsername(username);
    }
}
