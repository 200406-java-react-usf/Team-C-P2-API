package com.travelapp.web.dtos;

import com.travelapp.models.Role;
import com.travelapp.models.User;

import java.util.Objects;

public class Principal {

    private int id;
    private String username;
    private String role;

    public Principal() {
        super();
    }

    public Principal(UserDto u) {
        this.id = u.getId();
        this.username = u.getUsername();
        this.role = u.getRole();
    }

    public Principal(User u) {
        this.id = u.getId();
        this.username = u.getUsername();
        this.role = u.getRole();
    }

    public Principal(int id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Principal principal = (Principal) o;
        return id == principal.id &&
                Objects.equals(username, principal.username) &&
                role == principal.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, role);
    }

    @Override
    public String toString() {
        return "Principal{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role=" + role +
                '}';
    }
}
