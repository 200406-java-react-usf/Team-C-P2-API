package com.travelapp.models;

public class Recommendation {
    private int ticketId;
    private String destination;
    private String description;

    public Recommendation(int ticketId, String destination, String description) {
        this.ticketId = ticketId;
        this.destination = destination;
        this.description = description;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Recommendation{" +
                "ticketId=" + ticketId +
                ", destination='" + destination + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
