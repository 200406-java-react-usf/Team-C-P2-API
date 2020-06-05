package com.travelapp.services;

import com.travelapp.exceptions.BadRequestException;
import com.travelapp.exceptions.ResourceNotFoundException;
import com.travelapp.exceptions.ResourcePersistenceException;
import com.travelapp.models.Ticket;
import com.travelapp.repos.TicketRepository;
import com.travelapp.util.Validator;
import com.travelapp.web.dtos.TicketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.travelapp.util.Validator.*;

@Service
public class TicketService {

    private TicketRepository ticketRepo;

    @Autowired
    public TicketService(TicketRepository repo) { this.ticketRepo = repo; }

    @Transactional(readOnly=true)
    public List<TicketDto> getAll(){
        List<Ticket> tickets;
        try{
            tickets = ticketRepo.getAll();
        }
        catch (Exception e) {
            throw new ResourceNotFoundException();
        }

        List<TicketDto> ticketDtos = new ArrayList<>();
        for (Ticket t : tickets) {
            ticketDtos.add(new TicketDto(t));
        }

        return ticketDtos;
    }

    @Transactional(readOnly=true)
    public TicketDto getById(int id) {
        if(!isValidId(id)){
            throw new BadRequestException();
        }
        try {
            return new TicketDto(ticketRepo.findById(id));
        }
        catch (Exception e) {
            throw new ResourceNotFoundException();
        }

    }

    @Transactional
    public TicketDto save(TicketDto ticketDto) {
        if(!isValidTicket(ticketDto)){
            throw new BadRequestException();
        }
        Ticket ticket = new Ticket(ticketDto);
        try {
            return new TicketDto(ticketRepo.save(ticket));
        }
        catch (Exception e) {
            throw new ResourcePersistenceException();
        }


    }

    @Transactional
    public boolean update(Ticket ticket) {
        if(!isValidUpdateTicket(ticket)){
            throw new BadRequestException();
        }
        try {
            return ticketRepo.update(ticket);
        }
        catch (Exception e) {
            throw new ResourcePersistenceException("Ticket could not be updated");
        }

    }

    @Transactional
    public boolean deleteById(int id) {
        if(!isValidId(id)){
            throw new BadRequestException();
        }
        try {
            return ticketRepo.deleteById(id);
        }
        catch (Exception e) {
            return true;
        }
    }

}
