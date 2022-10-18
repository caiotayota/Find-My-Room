package com.caiotayota.findaroom.controlers;

import com.caiotayota.findaroom.entities.Ad;
import com.caiotayota.findaroom.services.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/ads", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdController {

    private final AdService service;

    @Autowired
    public AdController(AdService service) {
        this.service = service;
    }

    @GetMapping
    public List<Ad> findProperties() {
        return service.findAds();
    }
}
