package com.travelapp.services;

import com.travelapp.exceptions.BadRequestException;
import com.travelapp.models.User;
import com.travelapp.repos.UserRepository;
import com.travelapp.web.dtos.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService{

    private UserRepository userRepo;

    @Autowired
    public UserService(UserRepository repo) {
        super();
        this.userRepo = repo;
    }

    @Transactional(readOnly=true)
    public List<User> getAllUsers() {
        return userRepo.getAll();
    }

    @Transactional
    public User getById(int id) { return userRepo.findById(id); }

//    @Transactional
//    public boolean updateUser(User updatedUser) {
//        return userRepo.updateUser(updatedUser);
//    }

    @Transactional
    public boolean deleteUserById(int id) {
        if(id <= 0){
            throw new BadRequestException();
        }
        return userRepo.deleteById(id);
    }

    @Transactional
    public User findUserByCredentials(Credentials creds) {
        return userRepo.findUserByCredentials(creds);
    }

    @Transactional
    public User saveNewUser(User newUser) {

        return userRepo.save(newUser);
    }
}
