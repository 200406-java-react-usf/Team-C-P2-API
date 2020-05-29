package com.travelapp.repos;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public UserRepository(SessionFactory factory) {
        super();
        this.sessionFactory = factory;
    }


}
