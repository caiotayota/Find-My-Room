package com.caiotayota.findaroom.services;

import com.caiotayota.findaroom.entities.User;
import com.caiotayota.findaroom.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findProperties() {
        return repository.findAll();
    }
}
