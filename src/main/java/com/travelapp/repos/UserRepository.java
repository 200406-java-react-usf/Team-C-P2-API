package com.travelapp.repos;

import com.travelapp.models.Role;
import com.travelapp.models.Ticket;
import com.travelapp.models.User;
import com.travelapp.util.Validator;
import com.travelapp.web.dtos.Credentials;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

import static com.travelapp.util.Validator.isEmptyString;

@Repository
public class UserRepository implements CrudRepository<User> {


    private SessionFactory sessionFactory;

    @Autowired
    public UserRepository(SessionFactory factory) {
        super();
        this.sessionFactory = factory;
    }

    public User findUserByCredentials(Credentials credentials) {

        Session session = sessionFactory.getCurrentSession();
        User validUser = session.createQuery("from User u " +
                "where u.username = :un and u.password = :pw", User.class)
                .setParameter("un", credentials.getUsername())
                .setParameter("pw", credentials.getPassword()).getSingleResult();


        return validUser;
    }

    public List<Ticket> getUserTickets(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        List<Ticket> tickets = user.getTickets();
        return tickets;
    }

    @Override
    public User save(User newUser){

        Session session = sessionFactory.getCurrentSession();
        newUser.setRole(session.get(Role.class, 2));
        session.save(newUser);
        return newUser;


    }

    @Override
    public List<User> getAll() {

            Session session = sessionFactory.getCurrentSession();
            return session.createNativeQuery("select * from users", User.class).getResultList();

    }

    @Override
    public User findById(int id) {

        Session session = sessionFactory.getCurrentSession();
        User validUser = session.createQuery("from User u " +
                "where u.id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();

        return validUser;
    }


    @Override
    public boolean update(User updatedUser){

        Session session = sessionFactory.getCurrentSession();

        User user = session.get(User.class, updatedUser.getId());
        user.setUsername(updatedUser.getUsername());
        if(!isEmptyString(updatedUser.getPassword())){
            user.setPassword(updatedUser.getPassword());
        }
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        if (updatedUser.getRole().equals("Admin")) {
            Role role = session.find(Role.class, 1);
            user.setRole(role);
        } else if (updatedUser.getRole().equals("User")) {
            Role role = session.find(Role.class, 2);
            user.setRole(role);
        }
        session.update(user);

        return true;
    }

    public boolean deleteById(int id){

        Session session = sessionFactory.getCurrentSession();
        User deletedUser = session.find(User.class, id);

        //Get associations
        Role admin = session.find(Role.class, 1);
        Role user = session.find(Role.class, 2);
        List<User> adminUsers = admin.getUsers();
        List<User> users = user.getUsers();
        //Remove object from associations
        adminUsers.removeIf(u-> u.getId() == id);
        users.removeIf(u-> u.getId() == id);

        session.remove(deletedUser);
        //session.flush();
        return true;
    }



}
