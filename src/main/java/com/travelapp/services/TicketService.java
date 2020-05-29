package com.travelapp.services;

import com.travelapp.models.Ticket;
import com.travelapp.repos.TicketRepository;

import java.util.List;

public class TicketService {

    private TicketRepository ticketRepo;

    public TicketService(TicketRepository repo) { this.ticketRepo = repo; }

    public List<Ticket> getAll(){
        return ticketRepo.getAll();
    }

    public Ticket getById(int id) {
        return ticketRepo.findById(id);
    }

    public Ticket save(Ticket ticket) {
        return ticketRepo.save(ticket);
    }

    public boolean update(Ticket ticket) {
        return ticketRepo.update(ticket);
    }

    public boolean deleteById(int id) {
        return ticketRepo.deleteById(id);
    }

}
