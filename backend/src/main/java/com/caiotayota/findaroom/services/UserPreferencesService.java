package com.caiotayota.findaroom.services;

import com.caiotayota.findaroom.entities.UserPreferences;
import com.caiotayota.findaroom.repositories.UserPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPreferencesService {

    private final UserPreferencesRepository repository;

    @Autowired
    public UserPreferencesService(UserPreferencesRepository repository) {
        this.repository = repository;
    }

    public List<UserPreferences> findProperties() {
        return repository.findAll();
    }
}
