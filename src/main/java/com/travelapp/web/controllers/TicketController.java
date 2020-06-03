package com.travelapp.web.controllers;

import com.travelapp.models.Ticket;
import com.travelapp.models.User;
import com.travelapp.services.TicketService;
import com.travelapp.web.dtos.TicketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/tickets")
public class TicketController {

    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService service) {
        this.ticketService = service;
    }

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public List<TicketDto> getAllTickets(HttpServletRequest req) {

        return ticketService.getAll();
    }

    @GetMapping(value = "/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public TicketDto getTicketById(@PathVariable int id) {

        return ticketService.getById(id);
    }

    @PostMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public TicketDto registerNewTicket(@RequestBody TicketDto newTicket) {
        newTicket.setArrivalTime(new Date());
        newTicket.setDepartureTime(new Date());
        return ticketService.save(newTicket);
    }

    @PutMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public boolean updateTicket(@RequestBody Ticket updatedTicket) { return ticketService.update(updatedTicket); }

    @DeleteMapping("/{id}")
    public boolean deleteTicket(@PathVariable int id) { return ticketService.deleteById(id); }

}
