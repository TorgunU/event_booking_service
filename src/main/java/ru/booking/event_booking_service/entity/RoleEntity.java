package ru.booking.event_booking_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotEmpty
    @NotNull
    private String name;

    @OneToMany()
    private List<UserEntity> userEntities;

    public RoleEntity() {
    }

    public RoleEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addUser(UserEntity userEntity) {
        if (userEntities.isEmpty()) {
            userEntities = new LinkedList<>();
        } else if(userEntities.contains(userEntity)) {
            throw new IllegalArgumentException("User contains in users list!");
        }
        userEntities.add(userEntity);
        userEntity.setRole(this);
    }
}
