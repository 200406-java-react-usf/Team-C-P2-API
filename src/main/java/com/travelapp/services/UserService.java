package com.travelapp.services;

import com.travelapp.exceptions.BadRequestException;
import com.travelapp.exceptions.ResourceNotFoundException;
import com.travelapp.exceptions.ResourcePersistenceException;
import com.travelapp.exceptions.TravelappException;
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
        List<User> users;
        try {
            users = userRepo.getAll();
        }
        catch (Exception e) {
            throw new ResourceNotFoundException();
        }
        List<UserDto> userDtos = new ArrayList<>();
        for (User u : users) {
            userDtos.add(new UserDto(u));
        }

        return userDtos;
    }

    @Transactional(readOnly = true)
    public UserDto getById(int id) {
        if(!isValidId(id)){
            throw new BadRequestException();
        }
        User user = userRepo.findById(id);
        if(isEmptyObj(user)){
            throw new ResourceNotFoundException();
        }
        return new UserDto(user);
    }

    @Transactional(readOnly=true)
    public List<TicketDto> getUserTickets(int id) {

        if(!isValidId(id)){
            throw new BadRequestException();
        }

        List<Ticket> tickets;
        try {
            tickets = userRepo.getUserTickets(id);
        }
        catch (Exception e) {
            throw new ResourceNotFoundException();
        }

        List<TicketDto> ticketDtos = new ArrayList<>();
        for (Ticket t : tickets) {
            ticketDtos.add(new TicketDto(t));
        }

        return ticketDtos;
    }
    @Transactional(readOnly=true)
    public Principal findUserByCredentials(Credentials creds) {
        if(isEmptyString(creds.getUsername()) || isEmptyString(creds.getPassword())){
            throw new BadRequestException();
        }
        try{
            return new Principal(userRepo.findUserByCredentials(creds));
        }
        catch (Exception e){
            throw new ResourceNotFoundException();
        }

    }

    @Transactional
    public UserDto saveNewUser(User newUser) {
        if (isEmptyObj(newUser)) {
            throw new BadRequestException();
        }
        if (!isValidUser(newUser)) {
            throw new BadRequestException();
        }
        if (!isValidEmail(newUser.getEmail())) {
            throw new BadRequestException("Not a valid email");
        }
        try {
            User user = userRepo.save(newUser);
            return new UserDto(user);
        }
        catch (Exception e) {
            throw new ResourcePersistenceException("Could not persist user");
        }

    }

    @Transactional
    public boolean updateUser(User updatedUser) {
        if (isEmptyObj(updatedUser)) {
            throw new BadRequestException();
        }
        if (!updatedUser.getRole().equals("Admin") && !updatedUser.getRole().equals("User")) {
            throw new BadRequestException("Invalid Role Provided");
        }
        if(!isValidUpdatedUser(updatedUser)){
            throw new BadRequestException();
        }
        try{
            return userRepo.update(updatedUser);
        }
        catch (Exception e) {
            throw new ResourcePersistenceException();
        }

    }

    @Transactional
    public boolean deleteUserById(int id) {
        if(!isValidId(id)){
            throw new BadRequestException();
        }
        try{
            return userRepo.deleteById(id);
        }
        catch (Exception e){
            return true;
        }

    }
}
