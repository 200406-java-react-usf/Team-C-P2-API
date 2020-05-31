package com.travelapp.services;

import com.travelapp.models.Ticket;
import com.travelapp.repos.TicketRepository;
import com.travelapp.web.dtos.TicketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketService {

    private TicketRepository ticketRepo;

    @Autowired
    public TicketService(TicketRepository repo) { this.ticketRepo = repo; }

    @Transactional(readOnly=true)
    public List<Ticket> getAll(){
        return ticketRepo.getAll();
    }

    @Transactional(readOnly=true)
    public Ticket getById(int id) {
        return ticketRepo.findById(id);
    }

    @Transactional
    public Ticket save(TicketDto tempTicket) {
        Ticket ticket = new Ticket();
        ticket.setCost(tempTicket.getCost());
        ticket.setOrigin(tempTicket.getOrigin());
        ticket.setDestination(tempTicket.getDestination());
        ticket.setDepartureTime(tempTicket.getDepartureTime());
        ticket.setArrivalTime(tempTicket.getArrivalTime());
        int user_id = tempTicket.getAuthor_id();
        return ticketRepo.save(ticket, user_id);
    }

    @Transactional
    public boolean update(Ticket ticket) {
        return ticketRepo.update(ticket);
    }

    @Transactional
    public boolean deleteById(int id) {
        return ticketRepo.deleteById(id);
    }

}
