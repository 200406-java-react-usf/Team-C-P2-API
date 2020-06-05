package com.travelapp.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.travelapp.web.dtos.TicketDto;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "app_tickets")
public class Ticket {

    // Variable Declaration

    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private double cost;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    @JsonSerialize(using = DateSerializer.class)
    private Date departureTime;

    @Column(nullable = false)
    @JsonSerialize(using = DateSerializer.class)
    private Date arrivalTime;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    private User author;
//
//    @OneToMany(mappedBy = "Recommendations")
//    private List<Recommendation> recommendations;

    // Constructors
    public Ticket() {

    }

    public Ticket(double cost, String origin, String destination, Date departureTime, Date arrivalTime) {
        this.cost = cost;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public Ticket(int id, double cost, String origin, String destination, Date departureTime, Date arrivalTime) {
        this(cost, origin, destination, departureTime, arrivalTime);
        this.id = id;
    }

    public Ticket(double cost, String origin, String destination, Date departureTime, Date arrivalTime, User author) {
        this(cost, origin, destination, departureTime, arrivalTime);
        this.author = author;
    }

    public Ticket(int id, double cost, String origin, String destination, Date departureTime, Date arrivalTime, User author) {
        this(id, cost, origin, destination, departureTime, arrivalTime);
        this.author = author;
    }

    public Ticket(TicketDto t) {
        this(t.getCost(), t.getOrigin(), t.getDestination(), t.getDepartureTime(), t.getArrivalTime());
        this.author = new User(t.getAuthor_id());
    }
    //
//    public Ticket(double cost, String origin, String destination, Date departureTime, Date arrivalTime, List<Recommendation> recommendations) {
//        this(cost, origin, destination, departureTime, arrivalTime);
//        this.recommendations = recommendations;
//    }
//
//    public Ticket(double cost, String origin, String destination, Date departureTime, Date arrivalTime, User author, List<Recommendation> recommendations) {
//        this(cost, origin, destination, departureTime, arrivalTime);
//        this.author = author;
//        this.recommendations = recommendations;
//    }

    // Getters/Setters

    public int getId() {
        return id;
    }

    public Ticket setId(int id) {
        this.id = id;
        return this;
    }

    public double getCost() {
        return cost;
    }

    public Ticket setCost(double cost) {
        this.cost = cost;
        return this;
    }

    public String getOrigin() {
        return origin;
    }

    public Ticket setOrigin(String origin) {
        this.origin = origin;
        return this;
    }

    public String getDestination() {
        return destination;
    }

    public Ticket setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public Ticket setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
        return this;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public Ticket setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
        return this;
    }

    public User getAuthor() {
        return author;
    }

    public Ticket setAuthor(User author) {
        this.author = author;
        return this;
    }
//
//    public List<Recommendation> getRecommendations() {
//        return recommendations;
//    }
//
//    public Ticket setRecommendations(List<Recommendation> recommendations) {
//        this.recommendations = recommendations;
//        return this;
//    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id &&
                Double.compare(ticket.cost, cost) == 0 &&
                Objects.equals(origin, ticket.origin) &&
                Objects.equals(destination, ticket.destination) &&
                Objects.equals(departureTime, ticket.departureTime) &&
                Objects.equals(arrivalTime, ticket.arrivalTime) &&
                Objects.equals(author, ticket.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cost, origin, destination, departureTime, arrivalTime, author);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", cost=" + cost +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
//                ", author=" + author +
                '}';
    }
}
