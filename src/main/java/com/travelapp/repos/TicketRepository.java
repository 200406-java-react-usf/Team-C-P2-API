package com.travelapp.repos;


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

    @Override
    public Ticket save(Ticket newObj) {
        return null;
    }

    //This needs to be fixed to follow the crudRepository implementation
    public Ticket save(Ticket ticket, int id) {

        Session session = sessionFactory.getCurrentSession();

        System.out.println(id);
        try{
            User user = session.load(User.class, id);
            System.out.println(user);
            ticket.setAuthor(user);
            user.addTickets(ticket);
//
//            session.save(ticket);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Nothing");
        }


        return ticket;
    }

    @Override
    public boolean update(Ticket updatedTicket) {
        try (Session session = sessionFactory.getCurrentSession()) {

            session.update(updatedTicket);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean deleteById(int id) {
        try (Session session = sessionFactory.getCurrentSession()) {


            Ticket retrievedTicket = session.get(Ticket.class, id);
            System.out.println(retrievedTicket);
            session.delete(retrievedTicket);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
