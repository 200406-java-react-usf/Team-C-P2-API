package com.travelapp.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

public class Role {

    // Variable Declaration

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String role;

    // Constructors

    public Role(String role) {
        this.role = role;
    }

    // Getters/Setters

    public int getId() {
        return id;
    }

    public Role setId(int id) {
        this.id = id;
        return this;
    }

    public String getRole() {
        return role;
    }

    public Role setRole(String role) {
        this.role = role;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return id == role1.id &&
                Objects.equals(role, role1.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
