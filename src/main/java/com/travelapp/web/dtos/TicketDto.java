package com.travelapp.web.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import java.util.Date;
import java.util.Objects;

public class TicketDto {

    private int id;
    private double cost;
    private String origin;
    private String destination;
    @JsonSerialize(using = DateSerializer.class)
    private Date departureTime;
    @JsonSerialize(using = DateSerializer.class)
    private Date arrivalTime;
    private int author_id;

    public TicketDto() {

    }

    public TicketDto(double cost, String origin, String destination, Date departureTime, Date arrivalTime) {
        this.cost = cost;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public TicketDto(double cost, String origin, String destination, Date departureTime, Date arrivalTime, int author_id) {
        this.cost = cost;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.author_id = author_id;
    }

    public TicketDto(int id, double cost, String origin, String destination, Date departureTime, Date arrivalTime, int author_id) {
        this.id = id;
        this.cost = cost;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.author_id = author_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketDto ticketDto = (TicketDto) o;
        return id == ticketDto.id &&
                Double.compare(ticketDto.cost, cost) == 0 &&
                author_id == ticketDto.author_id &&
                Objects.equals(origin, ticketDto.origin) &&
                Objects.equals(destination, ticketDto.destination) &&
                Objects.equals(departureTime, ticketDto.departureTime) &&
                Objects.equals(arrivalTime, ticketDto.arrivalTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cost, origin, destination, departureTime, arrivalTime, author_id);
    }

    @Override
    public String toString() {
        return "TicketDto{" +
                "id=" + id +
                ", cost=" + cost +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", author_id=" + author_id +
                '}';
    }
}
