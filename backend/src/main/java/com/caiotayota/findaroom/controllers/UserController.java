package com.caiotayota.findaroom.controllers;

import com.caiotayota.findaroom.entities.User;
import com.caiotayota.findaroom.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/user", produces = APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService userService) {
        this.service = userService;
    }

    @GetMapping("/{email}")
    public Optional<User> getUser(@PathVariable String email) {
        return service.getUser(email);
    }

    @GetMapping
    public List<User> getUsers() {
        return service.getUsers();
    }

    @PostMapping(value = "/register", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public User createUser(@Valid @RequestBody User user) {
        return service.createUser(user);
    }

    @PutMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@PathVariable String email, @RequestBody @Valid User updatedUser) {
        return service.updateUser(email, updatedUser);
    }

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String email) {
        service.deleteUser(email);
    }
}
