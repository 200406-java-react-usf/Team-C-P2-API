package com.travelapp.services;

import com.travelapp.exceptions.ResourceNotFoundException;
import com.travelapp.models.Ticket;
import com.travelapp.web.dtos.TicketDto;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class TicketServiceTest {

    @Test
    public List<TicketDto> getAll(){
        return null;
    }

    @Test
    public TicketDto getById(int id) {
        return null;
    }

    @Test
    public TicketDto save(TicketDto ticketDto) {
        return null;

    }

    @Test
    public boolean update(Ticket ticket) {
        return false;
    }

    @Test
    public boolean deleteById(int id) {
        return false;
    }
}
