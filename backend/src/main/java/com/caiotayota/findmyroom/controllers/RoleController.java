package com.caiotayota.findmyroom.controllers;

import com.caiotayota.findmyroom.entities.Role;
import com.caiotayota.findmyroom.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = {"http://localhost:5173", "https://findmyroom.ie"})
@RestController
@RequestMapping(value = "/api/role")
public class RoleController {

    private RoleService service;

    @PostMapping(value = "/new", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Role createRole(@Valid @RequestBody Role role) {
        return service.saveRole(role);
    }
}
