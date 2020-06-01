package com.travelapp.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Role {

    // Variable Declaration

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String roleName;



    // Constructors

    public Role() {

    }

    public Role(String role) {
        this.roleName = role;
    }

    public Role(int id, String role) {
        this.id = id;
        this.roleName = role;
    }

    // Getters/Setters

    public int getId() {
        return id;
    }

    public Role setId(int id) {
        this.id = id;
        return this;
    }

    public String getRoleName() {
        return roleName;
    }

    public Role setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return id == role1.id &&
                Objects.equals(roleName, role1.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + roleName + '\'' +
                '}';
    }
}
