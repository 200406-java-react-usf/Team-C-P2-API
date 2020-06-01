package com.travelapp.repos;

import com.travelapp.models.Role;
import com.travelapp.models.User;
import com.travelapp.web.dtos.Credentials;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class UserRepository {


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

    public User save(User newUser){

        Session session = sessionFactory.getCurrentSession();
        if (newUser.getRole().equals("Admin")) {
            newUser.setRole(session.get(Role.class, 1));
        } else if (newUser.getRole().equals("User")) {
            newUser.setRole(session.get(Role.class, 2));
        }
        session.save(newUser);
        return newUser;


    }

    public List<User> getAll() {

            Session session = sessionFactory.getCurrentSession();
            return session.createNativeQuery("select * from users",User.class).getResultList();

    }

//    public boolean updateUser(User updatedUser){
//        Transaction transaction = null;
//
//        try(Session session = sessionFactory.getCurrentSession()) {
//
//            transaction = session.beginTransaction();
//            Query query = session.createQuery("update User u set u.password = :pw " +
//                    ", u.first_name = :fn , u.last_name = :ln , u.email = :email , u.role = :role");
//
//            query.setParameter("pw", updatedUser.getPassword())
//                    .setParameter("fn", updatedUser.getFirstName())
//                    .setParameter("ln", updatedUser.getLastName())
//                    .setParameter("email", updatedUser.getEmail())
//                    .setParameter("role", updatedUser.getRole());
//
//            transaction.commit();
//            return true;
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            return false;
//        }
//    }

    public boolean deleteById(int id){
        try(Session session = sessionFactory.getCurrentSession()) {

            User deletedUser;
            session.createQuery("delete users where id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }



}
