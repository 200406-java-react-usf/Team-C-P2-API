package com.travelapp.services;

import com.travelapp.exceptions.BadRequestException;
import com.travelapp.exceptions.ResourceNotFoundException;
import com.travelapp.models.User;
import com.travelapp.repos.UserRepository;
import com.travelapp.web.dtos.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.travelapp.util.Validator.*;

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
        List<User> users = userRepo.getAll();
        if(isEmptyList(users)){
            throw new ResourceNotFoundException();
        }
        return users;
    }

    @Transactional
    public User getById(int id) {
        if(!isValidId(id)){
            throw new BadRequestException();
        }
        User user = userRepo.findById(id);
        if(isEmptyObj(user)){
            throw new ResourceNotFoundException();
        }
        return user;
    }

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
        if(isValidUser(newUser)){
            throw new BadRequestException();
        }
        if(isValidEmail(newUser.getEmail())){
            throw new BadRequestException("Not a valid email");
        }

        return userRepo.save(newUser);
    }
}
