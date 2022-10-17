package com.caiotayota.findaroom.services;

import com.caiotayota.findaroom.entities.Ad;
import com.caiotayota.findaroom.repositories.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdService {

    private final AdRepository repository;

    @Autowired
    public AdService(AdRepository repository) {
        this.repository = repository;
    }

    public List<Ad> findProperties() {
        return repository.findAll();
    }
}
