package ru.booking.event_booking_service.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.booking.event_booking_service.entity.RoleEntity;
import ru.booking.event_booking_service.entity.UserEntity;
import ru.booking.event_booking_service.model.User;
import ru.booking.event_booking_service.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository repository;
    private ModelMapper modelMapper;
    private RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository repository, ModelMapper modelMapper, RoleService roleService) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
    }

    @Override
    public User getUserByUsername(String username) {
        UserEntity userEntity = repository.findAll().stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User wasn't founded " +
                        "by name: " + username));
        return modelMapper.map(userEntity, User.class);
    }

    @Override
    public User getUserById(Long id) {
        UserEntity foundEntity = repository.findAll().stream()
                .filter(userEntity -> id.equals(userEntity.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User wasn't founded " +
                        "by id: " + id));
        return modelMapper.map(foundEntity, User.class);
    }

    @Override
    public void saveUser(User user) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        repository.save(userEntity);
        RoleEntity roleEntity = userEntity.getRole();
        roleEntity.addUser(userEntity);
    }

    @Override
    public boolean isUserExist(String username) {
        Optional<UserEntity> optionalUserEntity = repository.findAll().stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();
        return optionalUserEntity.isPresent();
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll().stream()
                .map(userEntity -> modelMapper.map(userEntity, User.class))
                .toList();
    }
}
