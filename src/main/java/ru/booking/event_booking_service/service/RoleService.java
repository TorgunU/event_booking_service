package ru.booking.event_booking_service.service;

import org.modelmapper.ModelMapper;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.stereotype.Service;
import ru.booking.event_booking_service.entity.RoleEntity;
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
                .map(roleEntity -> modelMapper.map(roleEntity, Role.class))
                .toList();
    }

    public Role getRoleByName(String name) {
        RoleEntity roleEntity = roleRepository.findByName(name);
        if (roleEntity == null) {
            throw new DataSourceLookupFailureException(name + " not contains in Roles");
        }
        return modelMapper.map(roleEntity, Role.class);
    }
}
