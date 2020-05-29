package com.travelapp.repos;

import com.travelapp.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public UserRepository(SessionFactory factory) {
        super();
        this.sessionFactory = factory;
    }

    public User findUserByCredentials(User credentials) {
        User validUser = null;

        try(Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();
            validUser = (User) session.createQuery("from User u " +
                    "where u.username = :un and u.password = :pw", User.class)
                    .setParameter("un" , credentials.getUsername())
                    .setParameter("pw", credentials.getPassword());

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return validUser;
    }

    public List<User> getAll() {
        List<User> allUsers = null;
        try(Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();
            allUsers = session.createQuery("from User",User.class).list();

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return allUsers;
    }

    public boolean updateUser(User updatedUser){
        Transaction transaction = null;

        try(Session session = sessionFactory.getCurrentSession()) {

            transaction = session.beginTransaction();
            Query query = session.createQuery("update User u set u.password = :pw " +
                    ", u.first_name = :fn , u.last_name = :ln , u.email = :email , u.role = :role");

            query.setParameter("pw", updatedUser.getPassword())
                    .setParameter("fn", updatedUser.getFirstName())
                    .setParameter("ln", updatedUser.getLastName())
                    .setParameter("email", updatedUser.getEmail())
                    .setParameter("role", updatedUser.getRole());

            transaction.commit();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            return false;
        }
    }

    public boolean deleteById(int id){
        Transaction transaction = null;
        User deletedUser;
        try(Session session = sessionFactory.getCurrentSession()) {

            transaction = session.beginTransaction();
            deletedUser = session.get(User.class, id);
            session.delete(deletedUser);
            transaction.commit();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            return false;
        }
    }



}
