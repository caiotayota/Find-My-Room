package com.caiotayota.findmyroom.services;

import com.caiotayota.findmyroom.entities.Role;
import com.caiotayota.findmyroom.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository repository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.repository = roleRepository;
    }

    public Role saveRole(Role role) {
        return repository.save(role);
    }

}
