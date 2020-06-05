package com.travelapp.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "users",schema = "public")
public class User {

    // Variable declaration

    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Role role;

    @OneToMany(cascade = ALL, mappedBy = "author", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Ticket> tickets;

    //Constructors

    public User() {

    }

    public User(int id) {
        this.id = id;
    }

    public User(String username, String password, String firstName, String lastName, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User(String username, String password, String firstName, String lastName, String email, Role role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    public User(int id, String username, String password, String firstName, String lastName, String email, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    public User(int id, String username, String password, String firstName, String lastName, String email, Role role, List<Ticket> tickets) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.tickets = tickets;
    }

    //Copy a user, but doesn't copy their tickets
    public User(User u) {
        this(u.id, u.username, u.password, u.firstName, u.lastName, u.email, u.role);
    }

    // Getters/Setters

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getRole() {
        //return role to get both id and name
        return role.getRoleName();
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

//    public List<Integer> getTickets() {
//        List<Integer> res = new ArrayList<>();
//        for (Ticket ticket: tickets) {
//
//            res.add(ticket.getId());
//        }
//        return res;
//    }

    public User setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
        return this;
    }

    public void addTickets(Ticket ticket) {
        if (tickets == null) tickets = new ArrayList<>();
        ticket.setAuthor(this);
        tickets.add(ticket);
    }


    public List<Ticket> getTickets() {
        return tickets;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, firstName, lastName, email, role);
    }
}
