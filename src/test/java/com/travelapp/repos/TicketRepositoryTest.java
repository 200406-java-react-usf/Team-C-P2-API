package com.travelapp.repos;

import com.travelapp.models.Role;
import com.travelapp.models.Ticket;
import com.travelapp.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.annotation.Transactional;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@Transactional
//public class TicketRepositoryTest {
//
//    @Mock
//    private static SessionFactory sessionFactory;
//    private static List<Ticket> tickets;
//    private static TicketRepository sut;
//
//    @Before
//    public void beforeTest() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @BeforeClass
//    public static void setUp() {
//        sut = new TicketRepository(sessionFactory);
//
//        tickets = new ArrayList<>();
//        try {
//            Ticket a = new Ticket(1,100.50,"West Palm Beach","Tampa",
//                    new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2020"), new SimpleDateFormat("dd/MM/yyyy").parse("02/02/2020"),
//                    new User(1, "aanderson", "password", "Alex", "Anderson", "aanderson@gmail.com",
//                            new Role(1, "Admin")));
//            Ticket b = new Ticket(2,100.50,"West Palm Beach","Tampa",
//                    new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2020"), new SimpleDateFormat("dd/MM/yyyy").parse("02/02/2020"),
//                    new User(2, "admin", "password", "Alex", "Anderson", "aanderson@gmail.com",
//                            new Role(1, "Admin")));
//            tickets.add(a);
//            tickets.add(b);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//    @Test
//    public void getAllTest() throws ParseException {
//
//        Session session = mock(Session.class);
//        //Query query = mock(Query.class);
//        when(sessionFactory.getCurrentSession()).thenReturn(session);
//        when(session.createQuery("From Ticket").list()).thenReturn(tickets);
//        //when(query.list()).thenReturn(tickets);
//        List<Ticket> test = sut.getAll();
//        assertEquals(test.size(), 2);
//
//
//    }
//}
