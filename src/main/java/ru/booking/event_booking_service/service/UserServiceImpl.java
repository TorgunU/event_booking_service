package ru.booking.event_booking_service.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.booking.event_booking_service.entity.UserEntity;
import ru.booking.event_booking_service.model.User;
import ru.booking.event_booking_service.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository repository;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
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
    public void saveUser(User user) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        repository.save(userEntity);
    }

    @Override
    public boolean isUserExist(String name) {
        User user = getUserByUsername(name);
        return user != null;
    }

    @Override
    public void setRoleAdmin(Long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll().stream()
                .map(userEntity -> modelMapper.map(userEntity, User.class))
                .toList();
    }
}
