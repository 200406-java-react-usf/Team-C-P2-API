package com.travelapp.web.controllers;

public class TicketController {

    private UserService userService;

    @Autowired
    public UserController(UserService service) {
        super();
        this.userService = service;
    }

    @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public List<AppUser> getAllUsers(HttpServletRequest req) {
        return userService.getAllUsers();
    }

    @PostMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public AppUser registerNewUser(@RequestBody AppUser newUser) {
        return userService.register(newUser);
    }

    // localhost:8080/quizzard/users?id=1
    @GetMapping("/{id}")
    public AppUser getUserById(@PathVariable int id) {
        return null;
    }

}
