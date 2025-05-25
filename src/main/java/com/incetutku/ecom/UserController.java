package com.incetutku.ecom;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.fetchAllUsers();
    }

    @PostMapping
    public String createUser(@RequestBody User user) {
        userService.addUser(user);
        return "User added successfully.";
    }
}
