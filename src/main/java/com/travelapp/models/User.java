package com.travelapp.models;


import javax.persistence.*;
import java.util.Objects;

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

//    @Column
//    private Role role;
//
//    @Column
//    @OneToMany
//    private List<Ticket> tickets;

    //Constructors

    public User() {

    }

    public User(String username, String password, String firstName, String lastName, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User(int id, String username, String password, String firstName, String lastName, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    //    public User(String username, String password, String firstName, String lastName, String email, Role role) {
//        this.username = username;
//        this.password = password;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.role = role;
//    }

//    public User(String username, String password, String firstName, String lastName, String email, Role role, List<Ticket> tickets) {
//        this(username, password, firstName, lastName, email, role);
////        this.tickets = tickets;
//    }

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

//    public Role getRole() {
//        return role;
//    }
//
//    public User setRole(Role role) {
//        this.role = role;
//        return this;
//    }
//
//    public List<Ticket> getTickets() {
//        return tickets;
//    }
//
//    public User setTickets(List<Ticket> tickets) {
//        this.tickets = tickets;
//        return this;
//    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
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
                Objects.equals(email, user.email) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, firstName, lastName, email);
    }
}
