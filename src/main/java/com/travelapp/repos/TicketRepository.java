package com.travelapp.repos;


import com.travelapp.models.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
        List<Ticket> retrievedTickets = null;
        try (Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();

            retrievedTickets = session.createQuery("From AppUser", Ticket.class).list();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return retrievedTickets;
    }

    @Override
    public Ticket findById(int id) {
        Ticket retrievedTicket = null;
        try (Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();

            retrievedTicket = session.get(Ticket.class, id);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return retrievedTicket;
    }

    @Override
    public Ticket save(Ticket newTicket) {
        Transaction tx = null;
        try (Session session = sessionFactory.getCurrentSession()) {

            tx = session.beginTransaction();
            session.save(newTicket);
            tx.commit();


        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }

        return newTicket;
    }

    @Override
    public boolean update(Ticket updatedTicket) {
        Transaction tx = null;
        try (Session session = sessionFactory.getCurrentSession()) {

            tx = session.beginTransaction();

            session.update(updatedTicket);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }

        return false;
    }

    @Override
    public boolean deleteById(int id) {
        Transaction tx = null;
        try (Session session = sessionFactory.getCurrentSession()) {

            tx = session.beginTransaction();

            Ticket s = session.load(Ticket.class, id);
            session.delete(s);

            tx.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }

        return false;
    }
}
