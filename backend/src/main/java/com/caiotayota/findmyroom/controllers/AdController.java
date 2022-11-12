package com.caiotayota.findmyroom.controllers;

import com.caiotayota.findmyroom.entities.Ad;
import com.caiotayota.findmyroom.entities.User;
import com.caiotayota.findmyroom.services.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/ads", produces = APPLICATION_JSON_VALUE)
public class AdController {

    private final AdService service;

    @Autowired
    public AdController(AdService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Optional<Ad> getAd(@PathVariable long id) {
        return service.getAd(id);
    }

    @GetMapping
    public List<Ad> getAds() {
        return service.getAds();
    }

    @GetMapping(value = "/search/", params = "parking")
    public List<Ad> getAdsByParking(@RequestParam @Valid boolean parking) {
        return service.getAdsByParking(parking);
    }

    @GetMapping(value = "/search/", params = "user")
    public List<Ad> getAdsByUser(@RequestParam @Valid User user) {
        return service.getAdsByUser(user);
    }

    @PostMapping(path = "/new", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Ad createAd(@RequestBody @Valid Ad ad, @RequestParam long roomId) {
        return service.createAd(ad, roomId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Ad updateAd(@PathVariable long id, @RequestBody @Valid Ad updatedAd) {
        return service.updateAd(id, updatedAd);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAd(@PathVariable long id) {
        service.deleteAd(id);
    }
}
