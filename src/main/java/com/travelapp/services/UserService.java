package com.travelapp.services;

import com.travelapp.exceptions.BadRequestException;
import com.travelapp.exceptions.ResourceNotFoundException;
import com.travelapp.models.Ticket;
import com.travelapp.models.User;
import com.travelapp.repos.UserRepository;
import com.travelapp.web.dtos.Credentials;
import com.travelapp.web.dtos.TicketDto;
import com.travelapp.web.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.getAll();
        List<UserDto> userDtos = new ArrayList<>();
        if(isEmptyList(users)){
            throw new ResourceNotFoundException();
        }
        for (User u : users) { userDtos.add(new UserDto(u)); }

        return userDtos;
    }
    @Transactional(readOnly = true)
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

    @Transactional(readOnly=true)
    public List<TicketDto> getUserTickets(int id) {

        List<Ticket> tickets = userRepo.getUserTickets(id);
        List<TicketDto> ticketDtos = new ArrayList<>();
        for (Ticket t : tickets) { ticketDtos.add(new TicketDto(t)); }

        return ticketDtos;
    }
    @Transactional(readOnly=true)
    public UserDto findUserByCredentials(Credentials creds) {
        return new UserDto(userRepo.findUserByCredentials(creds));
    }

    @Transactional
    public User saveNewUser(User newUser) {
        if (isValidUser(newUser)) {
            throw new BadRequestException();
        }
        if (isValidEmail(newUser.getEmail())) {
            throw new BadRequestException("Not a valid email");
        }
        return userRepo.save(newUser);
    }

    @Transactional
    public boolean updateUser(User updatedUser) {

        if (!updatedUser.getRole().equals("Admin") && !updatedUser.getRole().equals("User")) {
            throw new BadRequestException("Invalid Role Provided");
        }

        return userRepo.update(updatedUser);
    }

    @Transactional
    public boolean deleteUserById(int id) {
        if(id <= 0){
            throw new BadRequestException();
        }
        return userRepo.deleteById(id);
    }
}
