package com.caiotayota.findaroom.controlers;

import com.caiotayota.findaroom.entities.Ad;
import com.caiotayota.findaroom.entities.User;
import com.caiotayota.findaroom.entities.UserPreferences;
import com.caiotayota.findaroom.services.AdService;
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

    @GetMapping
    public List<Ad> getAds() {
        return service.getAds();
    }

    @GetMapping("/{id}")
    public Optional<Ad> getAd(@PathVariable long id) {
        return service.getAd(id);
    }

    @PostMapping(path = "/create", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Ad createAd(@RequestBody @Valid Ad adToSave) {
        return service.createAd(adToSave);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAd(@PathVariable long id, @RequestBody @Valid Ad ad) {
        service.updateAd(id, ad);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAd(@PathVariable long id) {
        service.deleteAd(id);
    }
}
