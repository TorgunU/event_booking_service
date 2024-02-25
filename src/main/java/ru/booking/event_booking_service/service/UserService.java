package ru.booking.event_booking_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.booking.event_booking_service.buisnessEntity.User;
import ru.booking.event_booking_service.entity.UserEntity;
import ru.booking.event_booking_service.mapper.UserMapper;
import ru.booking.event_booking_service.repository.UserRepository;

import java.util.List;
import java.util.Optional;

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
        UserEntity userEntity = repository.findAll().stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User wasn't founded " +
                        "by name: " + username));
        return userMapper.mapFromEntityToUser.apply(userEntity);
    }

    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        UserEntity user = repository.findAll().stream()
                .filter(userEntity -> id.equals(userEntity.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User wasn't founded " +
                        "by id: " + id));
        return userMapper.mapFromEntityToUser.apply(user);
    }

    @Transactional
    public User saveUser(User user) {
        UserEntity userEntity = userMapper.mapFromUserToEntity.apply(user);
        repository.save(userEntity);
        return userMapper.mapFromEntityToUser.apply(userEntity);
    }

    @Transactional(readOnly = true)
    public boolean isUserExist(String username) {
        Optional<UserEntity> optionalUserEntity = repository.findAll().stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();
        return optionalUserEntity.isPresent();
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return repository.findAll().stream()
                .map(userEntity -> userMapper.mapFromEntityToUser.apply(userEntity))
                .toList();
    }
}
