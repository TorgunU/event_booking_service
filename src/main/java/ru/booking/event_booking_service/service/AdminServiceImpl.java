package ru.booking.event_booking_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.booking.event_booking_service.entity.RoleEntity;
import ru.booking.event_booking_service.entity.UserEntity;
import ru.booking.event_booking_service.repository.RoleRepository;
import ru.booking.event_booking_service.repository.UserRepository;

@Service
public class AdminServiceImpl implements AdminService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public AdminServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void setAdminRoleById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new DataSourceLookupFailureException("User id is not contains in BD"));
        if (userEntity.getRole().getName().equals("ROLE_ADMIN")) {
            throw new IllegalArgumentException("User is already admin");
        }
        RoleEntity adminRoleEntity = roleRepository.findByName("ROLE_ADMIN");
        if (adminRoleEntity == null) {
            throw new IllegalStateException("Admin role is not found");
        }
        userEntity.setRole(adminRoleEntity);
        userRepository.save(userEntity);
    }
}
