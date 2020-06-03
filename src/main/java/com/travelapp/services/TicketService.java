package com.travelapp.services;

import com.travelapp.models.Ticket;
import com.travelapp.repos.TicketRepository;
import com.travelapp.web.dtos.TicketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    private TicketRepository ticketRepo;

    @Autowired
    public TicketService(TicketRepository repo) { this.ticketRepo = repo; }

    @Transactional(readOnly=true)
    public List<TicketDto> getAll(){

        List<Ticket> tickets = ticketRepo.getAll();
        List<TicketDto> ticketDtos = new ArrayList<>();
        for (Ticket t : tickets) { ticketDtos.add(new TicketDto(t)); }

        return ticketDtos;
    }

    @Transactional(readOnly=true)
    public TicketDto getById(int id) {
        return new TicketDto(ticketRepo.findById(id));
    }

    @Transactional
    public TicketDto save(TicketDto ticketDto) {

        System.out.println(ticketDto.getArrivalTime());
        System.out.println(ticketDto.getDepartureTime());
        Ticket ticket = new Ticket(ticketDto);
        return new TicketDto(ticketRepo.save(ticket));

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
