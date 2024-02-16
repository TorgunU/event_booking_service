package ru.booking.event_booking_service.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.booking.event_booking_service.model.Role;
import ru.booking.event_booking_service.repository.RoleRepository;

import java.util.List;

@Service
public class RoleService {
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;

    public RoleService(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(roleEntity -> modelMapper.map(modelMapper, Role.class))
                .toList();
    }
}
