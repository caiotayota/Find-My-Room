package com.caiotayota.findaroom.services;

import com.caiotayota.findaroom.entities.Property;
import com.caiotayota.findaroom.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    private final PropertyRepository repository;

    @Autowired
    public PropertyService(PropertyRepository repository) {
        this.repository = repository;
    }

    public List<Property> findProperties() {
        return repository.findAll();
    }



}
