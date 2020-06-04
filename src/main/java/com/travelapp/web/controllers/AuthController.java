package com.travelapp.web.controllers;

import com.travelapp.models.User;
import com.travelapp.services.UserService;
import com.travelapp.web.dtos.Credentials;
import com.travelapp.web.dtos.Principal;
import com.travelapp.web.dtos.UserDto;
import com.travelapp.web.security.JwtConfig;
import com.revature.revaboards.web.security.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/json")
    public Principal authUser(@RequestBody Credentials creds, HttpServletResponse resp) {

        Principal payload = userService.findUserByCredentials(creds);
        resp.setHeader(JwtConfig.HEADER, TokenGenerator.createJwt(payload));

        return payload;
    }

}
