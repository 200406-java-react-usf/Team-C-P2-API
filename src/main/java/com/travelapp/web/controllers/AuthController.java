package com.travelapp.web.controllers;

import com.travelapp.models.User;
import com.travelapp.services.UserService;
import com.travelapp.web.dtos.Credentials;
import com.travelapp.web.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins="http://localhost:3000", allowedHeaders="*")
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;

    @Autowired
    public AuthController(UserService service) {
        super();
        this.userService = service;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto authUser(@RequestBody Credentials creds) {

        return userService.findUserByCredentials(creds);
    }

}
