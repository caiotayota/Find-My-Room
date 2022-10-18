package com.caiotayota.findaroom.services;

import com.caiotayota.findaroom.entities.User;
import com.caiotayota.findaroom.exceptions.UsernameExistsException;
import com.caiotayota.findaroom.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(User user) {
        Optional<User> userToSave = repository.findById(user.getEmail());
        if (userToSave.isPresent()) {
            throw new UsernameExistsException();
        } else {
            String encodedPassword = passwordEncoder.encode(user.getEncryptedPassword());
            user.setEncryptedPassword(encodedPassword);
            repository.save(user);
        }
    }
}
