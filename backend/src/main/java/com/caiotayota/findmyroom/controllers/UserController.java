package com.caiotayota.findmyroom.controllers;

import com.caiotayota.findmyroom.dto.EmailVerificationDto;
import com.caiotayota.findmyroom.entities.User;
import com.caiotayota.findmyroom.services.EmailService;
import com.caiotayota.findmyroom.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/user", produces = APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;
    private final EmailService emailService;

    @Autowired
    public UserController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PutMapping(value = "/change-password")
    @ResponseStatus(HttpStatus.OK)
    public String changePassword(@RequestBody User user) {
        return userService.changePassword(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@RequestBody @Valid User updatedUser) {
        return userService.updateUser(updatedUser);
    }

//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//    }

    @PostMapping("/verification-code")
    public String requestVerificationCode(@RequestParam("email") String email) {
        return emailService.requestVerificationCode(email);
    }

    @PutMapping(value = "/verify-email", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> verifyEmail(@RequestBody EmailVerificationDto verificationCodeDto) {
        return userService.verifyEmail(verificationCodeDto.getEmail(), verificationCodeDto.getVerificationCode());
    }

}
