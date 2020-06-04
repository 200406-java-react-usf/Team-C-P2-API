package com.travelapp.repos;


import com.travelapp.models.Role;
import com.travelapp.models.Ticket;
import com.travelapp.models.User;
import com.travelapp.web.dtos.TicketDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketRepository implements CrudRepository<Ticket> {

    private SessionFactory sessionFactory;

    @Autowired
    private TicketRepository(SessionFactory factory) {
        super();
        this.sessionFactory = factory;
    }

    @Override
    public List<Ticket> getAll() {


        Session session = sessionFactory.getCurrentSession();

        List<Ticket> retrievedTickets = session.createQuery("From Ticket", Ticket.class).list();


        return retrievedTickets;
    }

    @Override
    public Ticket findById(int id) {

        Session session = sessionFactory.getCurrentSession();
        Ticket retrievedTicket = session.get(Ticket.class, id);

        return retrievedTicket;
    }

    //This needs to be fixed to follow the crudRepository implementation
    public Ticket save(Ticket ticket) {

        Session session = sessionFactory.getCurrentSession();

        User user = session.load(User.class, ticket.getAuthor().getId());
        ticket.setAuthor(user);
        user.addTickets(ticket);
        session.save(ticket);

        return ticket;
    }

    @Override
    public boolean update(Ticket updatedTicket) {

        Session session = sessionFactory.getCurrentSession();

        Ticket ticket = session.get(Ticket.class, updatedTicket.getId());
        ticket.setCost(updatedTicket.getCost());
        ticket.setOrigin(updatedTicket.getOrigin());
        ticket.setDestination(updatedTicket.getDestination());
        ticket.setDepartureTime(updatedTicket.getDepartureTime());
        ticket.setArrivalTime(updatedTicket.getArrivalTime());
        //changing the author probably isn't really necessary in our database.
        //may need to remove the ticket from the author its a part of
        //and add the ticket to the new author's list of tickets
        //these commented lines vvv dont actually work
//        User author = session.get(User.class, ticket.getAuthor().getId());
//        ticket.setAuthor(author);
        session.update(ticket);

        return true;

    }

    @Override
    public boolean deleteById(int id) {

        Session session = sessionFactory.getCurrentSession();
        Ticket deletedTicket = session.find(Ticket.class, id);

        //Get associations
        User author = deletedTicket.getAuthor();
        //Remove object from associations
        author.getTickets().removeIf(t-> t.getId() == id);

        session.remove(deletedTicket);
        //session.flush();
        return true;
    }
}
