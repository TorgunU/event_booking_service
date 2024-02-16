package ru.booking.event_booking_service.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.booking.event_booking_service.dto.UserRegistrationDTO;
import ru.booking.event_booking_service.entity.RoleEntity;
import ru.booking.event_booking_service.model.Role;
import ru.booking.event_booking_service.model.User;
import ru.booking.event_booking_service.repository.RoleRepository;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {
    private ModelMapper modelMapper;
    private UserService userService;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserRegistrationServiceImpl(ModelMapper modelMapper,
                                       UserService userService,
                                       RoleRepository roleRepository,
                                       PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createNewUser(UserRegistrationDTO userRegistrationDTO) {
        userRegistrationDTO.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        User user = modelMapper.map(userRegistrationDTO, User.class);
        RoleEntity roleEntity = roleRepository.findByName("user");
        if (roleEntity == null) {
            throw new DataSourceLookupFailureException("Data source could not be obtained");
        }
        Role role = modelMapper.map(roleEntity, Role.class);
        user.setRole(role);
        userService.saveUser(user);
    }
}
