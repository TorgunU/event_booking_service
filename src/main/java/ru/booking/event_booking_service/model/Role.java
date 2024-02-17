package ru.booking.event_booking_service.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.LinkedList;
import java.util.List;

public class Role {

    @NotEmpty
    @NotNull
    private int id;

    @NotEmpty
    @NotNull
    private String name;

    private List<User> userList;

    public Role() {
    }

    public Role(int id, String name, List<User> userList) {
        this.id = id;
        this.name = name;
        this.userList = userList;
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

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public void addUser(User user) {
        if (userList.isEmpty()) {
            userList = new LinkedList<>();
        } else if (userList.contains(user)) {
            throw new IllegalArgumentException("This user is contains in userList");
        }
        userList.add(user);
        user.setRole(this);
    }

    public void removeUser(User user) {
        if (userList.isEmpty()) {
            throw new IllegalStateException("Userlist is empty");
        } else if (!userList.contains(user)) {
            throw new IllegalArgumentException("This user is not contains in userList");
        }
        userList.remove(user);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userList=" + userList +
                '}';
    }
}
