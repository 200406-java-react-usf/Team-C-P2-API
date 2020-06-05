package com.travelapp.services;

import com.travelapp.exceptions.BadRequestException;
import com.travelapp.exceptions.ResourceNotFoundException;
import com.travelapp.exceptions.ResourcePersistenceException;
import com.travelapp.exceptions.TravelappException;
import com.travelapp.models.Role;
import com.travelapp.models.Ticket;
import com.travelapp.models.User;
import com.travelapp.repos.TicketRepository;
import com.travelapp.web.dtos.TicketDto;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TicketServiceTest {

    @Mock
    private static TicketRepository mockRepo;
    private static TicketService sut;
    private static List<Ticket> mockTickets;

    @BeforeClass
    public static void setUp() throws ParseException {
        mockRepo = mock(TicketRepository.class);
        mockTickets = new ArrayList<>();
        sut = new TicketService(mockRepo);

        Ticket a = new Ticket(1,100.50,"West Palm Beach","Tampa",
                new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2020"), new SimpleDateFormat("dd/MM/yyyy").parse("02/02/2020"),
                new User(1, "aanderson", "password", "Alex", "Anderson", "aanderson@gmail.com",
                new Role(1, "Admin")));

        Ticket b = new Ticket(2,100.50,"West Palm Beach","orlando",
                new SimpleDateFormat("dd/MM/yyyy").parse("02/02/2020"), new SimpleDateFormat("dd/MM/yyyy").parse("03/03/2020"),
                new User(1, "aanderson", "password", "Alex", "Anderson", "aanderson@gmail.com",
                        new Role(1, "Admin")));

        Ticket c = new Ticket(3,100.50,"West Palm Beach","Gainesville",
                new SimpleDateFormat("dd/MM/yyyy").parse("03/03/2020"), new SimpleDateFormat("dd/MM/yyyy").parse("03/03/2020"),
                new User(2, "bbailey", "password", "Bob",
                        "Bailey", "bbailey@gmail.com", new Role(2, "User")));

        Ticket d = new Ticket(4,100.50,"West Palm Beach","Gainesville",
                new SimpleDateFormat("dd/MM/yyyy").parse("03/03/2020"), new SimpleDateFormat("dd/MM/yyyy").parse("03/03/2020"),
                new User(3, "ccalhoun", "password", "Charlie",
                        "Calhoun", "ccalhoun@gmail.com", new Role(2, "User")));

        mockTickets.add(a);
        mockTickets.add(b);
        mockTickets.add(c);
        mockTickets.add(d);

    }

    @Test
    public void getAllTest(){

        when(mockRepo.getAll()).thenReturn(mockTickets);

        List<TicketDto> tickets = sut.getAll();

        assertEquals(tickets.size(), 4);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getAllTestToThrowError(){
        List<Ticket> test = new ArrayList<>();
        when(mockRepo.getAll()).thenThrow();

        sut.getAll();
    }

    @Test
    public void getByIdTest() {

        when(mockRepo.findById(1)).thenReturn(mockTickets.get(1));

        TicketDto ticketDto = sut.getById(1);

        assert(ticketDto.equals(new TicketDto(mockTickets.get(1))));

    }

    @Test(expected = BadRequestException.class)
    public void getByIdTestToThrowBadRequestException() {

        when(mockRepo.findById(1)).thenThrow(new BadRequestException());

        sut.getById(-1);

    }

    @Test(expected = ResourceNotFoundException.class)
    public void getByIdTestToThrowRNF_Exception() {

        when(mockRepo.findById(5)).thenThrow(new ResourceNotFoundException());

        sut.getById(5);

    }

    @Test
    public void saveTest() {
        TicketDto test;
        try {
            test = new TicketDto(100,"WPB","Tampa",
                    new SimpleDateFormat("dd/MM/yyyy").parse("02/02/2020"),
                    new SimpleDateFormat("dd/MM/yyyy").parse("03/03/2020"), 2);

            when(mockRepo.save(new Ticket(test))).thenReturn(new Ticket(test));

            TicketDto ticketDto = sut.save(test);

            assertEquals(ticketDto, test);

        }
        catch (ParseException e) {

        }



    }

    @Test
    public void updateTest() {

        when(mockRepo.update(mockTickets.get(3))).thenReturn(true);

        boolean isUpdated = sut.update(mockTickets.get(3));

        assertTrue(isUpdated);
    }

    @Test(expected = BadRequestException.class)
    public void updateTestToThrowBadRequestException() {

        try {

            Ticket test = new Ticket(4,100.50,"","Gainesville",
                    new SimpleDateFormat("dd/MM/yyyy").parse("03/03/2020"), new SimpleDateFormat("dd/MM/yyyy").parse("03/03/2020"),
                    new User(3, "ccalhoun", "password", "Charlie",
                            "Calhoun", "ccalhoun@gmail.com", new Role(2, "User")));

            when(mockRepo.update(test)).thenThrow(new BadRequestException());
            sut.update(test);
        }
        catch (ParseException e){ }
    }

    @Test(expected = ResourcePersistenceException.class)
    public void updateTestToThrowRP_Exception() {

        try {

            Ticket test = new Ticket(4,100.50,"WPB","Gainesville",
                    new SimpleDateFormat("dd/MM/yyyy").parse("03/03/2020"), new SimpleDateFormat("dd/MM/yyyy").parse("03/03/2020"),
                    new User(3, "ccalhoun", "password", "Charlie",
                            "Calhoun", "ccalhoun@gmail.com", new Role(2, "User")));

            when(mockRepo.update(test)).thenThrow(new ResourcePersistenceException());
            sut.update(test);
        }
        catch (ParseException e){ }
    }

    @Test
    public void deleteByIdTest() {

        when(mockRepo.deleteById(4)).thenReturn(true);

        boolean isDeleted = sut.deleteById(4);

        assertTrue(isDeleted);

    }

    @Test(expected = BadRequestException.class)
    public void deleteByIdTestToThrowBadRequestException() {

        when(mockRepo.deleteById(4)).thenThrow(new BadRequestException());

        sut.deleteById(-1);


    }
}
