package com.travelapp.services;

import com.travelapp.exceptions.BadRequestException;
import com.travelapp.models.Ticket;
import com.travelapp.models.User;
import com.travelapp.repos.UserRepository;
import com.travelapp.web.dtos.Credentials;
import com.travelapp.web.dtos.Principal;
import com.travelapp.web.dtos.TicketDto;
import com.travelapp.web.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public List<UserDto> getAllUsers() {

        List<User> users = userRepo.getAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (User u : users) { userDtos.add(new UserDto(u)); }

        return userDtos;
    }


    @Transactional(readOnly=true)
    public UserDto getById(int id) { return new UserDto(userRepo.findById(id)); }


    @Transactional(readOnly=true)
    public Principal findUserByCredentials(Credentials creds) {
        return new Principal(userRepo.findUserByCredentials(creds));
    }

    @Transactional(readOnly=true)
    public List<TicketDto> getUserTickets(int id) {

        List<Ticket> tickets = userRepo.getUserTickets(id);
        List<TicketDto> ticketDtos = new ArrayList<>();
        for (Ticket t : tickets) { ticketDtos.add(new TicketDto(t)); }

        return ticketDtos;
    }

    @Transactional
    public UserDto saveNewUser(User newUser) {
        return new UserDto(userRepo.save(newUser));
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
