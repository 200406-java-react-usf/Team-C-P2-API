package com.travelapp.models;

import javax.persistence.*;
import java.util.Objects;

public class Recommendation {

    // Variable Declaration

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "Ticket")
    private Ticket ticket;

    // Constructors

    public Recommendation(String destination, String description) {
        this.destination = destination;
        this.description = description;
    }

    public Recommendation(String destination, String description, Ticket ticket) {
        this(description, destination);
        this.ticket = ticket;
    }

    // Getters/Setters

    public int getId() {
        return id;
    }

    public Recommendation setId(int id) {
        this.id = id;
        return this;
    }

    public String getDestination() {
        return destination;
    }

    public Recommendation setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Recommendation setDescription(String description) {
        this.description = description;
        return this;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public Recommendation setTicket(Ticket ticket) {
        this.ticket = ticket;
        return this;
    }

    @Override
    public String toString() {
        return "Recommendation{" +
                "id=" + id +
                ", destination='" + destination + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recommendation that = (Recommendation) o;
        return id == that.id &&
                Objects.equals(destination, that.destination) &&
                Objects.equals(description, that.description) &&
                Objects.equals(ticket, that.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, destination, description, ticket);
    }
}
