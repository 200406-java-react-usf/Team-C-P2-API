package com.travelapp.web.controllers;

import com.travelapp.models.Ticket;
import com.travelapp.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public List<Ticket> getAllUsers(HttpServletRequest req) {
        return ticketService.getAll();
    }

    @PostMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public Ticket registerNewUser(@RequestBody Ticket newTicket) {
        return ticketService.save(newTicket);
    }

    // localhost:8080/quizzard/users?id=1
    @GetMapping("/{id}")
    public Ticket getUserById(@PathVariable int id) {
        return null;
    }

}
