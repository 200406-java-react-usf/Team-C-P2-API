package com.travelapp.web.controllers;

import com.travelapp.models.Ticket;
import com.travelapp.models.User;
import com.travelapp.services.UserService;
import com.travelapp.web.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService service) {
        super();
        this.userService = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> getAllUsers(HttpServletRequest req) {
        //Get users
        List<User> users = userService.getAllUsers();
        //Format users for output
        List<UserDto> userDtos = new ArrayList<UserDto>();
        for (User u : users) {
            List<Ticket> ut = u.getTickets();
            List<Integer> utickets = new ArrayList<Integer>();
            for (Ticket ticket : ut) {
                utickets.add(ticket.getId());
            }
            userDtos.add(new UserDto(u.getId(), u.getUsername(), u.getPassword(), u.getFirstName(),
                    u.getLastName(), u.getEmail(), u.getRole(), utickets));
        }
        //Output users
        return userDtos;
    }

    @GetMapping(value = "/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public UserDto getUserById(@PathVariable int id) {
        //Get User
        User u = userService.getById(id);
        //Format user for output
        List<Ticket> ut = u.getTickets();
        List<Integer> utickets = new ArrayList<Integer>();
        for (Ticket ticket : ut) {
            utickets.add(ticket.getId());
        }
        UserDto userDto = new UserDto(u.getId(), u.getUsername(), u.getPassword(), u.getFirstName(),
                u.getLastName(), u.getEmail(), u.getRole(), utickets);
        //Output user
        return userDto;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto saveUser(@RequestBody User newUser){
        //Get User
        User u =  userService.saveNewUser(newUser);
        //Format user for output
        List<Integer> tickets = new ArrayList<>();
        UserDto userDto = new UserDto(u.getId(), u.getUsername(), u.getPassword(), u.getFirstName(),
                u.getLastName(), u.getEmail(), u.getRole(), tickets);

        //Output user
        return userDto;
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean updateUser(@RequestBody User updatedUser){
        return userService.updateUser(updatedUser);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteUser(@PathVariable int id){
        return userService.deleteUserById(id);
    }

}
