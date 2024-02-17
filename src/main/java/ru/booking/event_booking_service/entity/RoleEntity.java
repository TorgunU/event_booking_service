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

    @OneToMany(mappedBy = "roleEntity", fetch = FetchType.EAGER)
    private List<UserEntity> userEntities = new LinkedList<>();

    public RoleEntity() {
    }

    public RoleEntity(int id, String name, List<UserEntity> userEntities) {
        this.id = id;
        this.name = name;
        this.userEntities = userEntities;
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

    public List<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(List<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }

    public void addUser(UserEntity userEntity) {
        if(userEntities.contains(userEntity)) {
            throw new IllegalArgumentException("User contains in users list!");
        }
        userEntities.add(userEntity);
        userEntity.setRole(this);
    }

    public void removeUser(UserEntity userEntity) {
        if (userEntities.isEmpty()) {
            throw new IllegalArgumentException("Users list is empty!");
        } else if(userEntities.contains(userEntity)) {
            throw new IllegalArgumentException("User not contains in users list!");
        }
        userEntities.remove(userEntity);
        userEntity.setRole(this);
    }
}
