package com.travelapp.web.controllers;

import com.travelapp.models.Ticket;
import com.travelapp.models.User;
import com.travelapp.services.UserService;
import com.travelapp.web.dtos.TicketDto;
import com.travelapp.web.dtos.UserDto;
import com.travelapp.web.security.Secured;
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
    @Secured(allowedRoles = {"Admin"})
    public List<UserDto> getAllUsers() {

        return userService.getAllUsers();
    }

    @GetMapping(value = "/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    @Secured(allowedRoles = {"Admin"})
    public UserDto getUserById(@PathVariable int id) {

        return userService.getById(id);
    }

    @GetMapping(value = "/{id}/tickets", produces=MediaType.APPLICATION_JSON_VALUE)
    @Secured(allowedRoles = {"Admin", "User"})
    public List<TicketDto> getUserTickets(@PathVariable int id) {

        return userService.getUserTickets(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto saveUser(@RequestBody User newUser){

        return userService.saveNewUser(newUser);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Secured(allowedRoles = {"Admin", "User"})
    public boolean updateUser(@RequestBody User updatedUser){
        return userService.updateUser(updatedUser);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteUser(@PathVariable int id){
        return userService.deleteUserById(id);
    }

}
