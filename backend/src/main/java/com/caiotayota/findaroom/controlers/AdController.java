package com.caiotayota.findaroom.controlers;

import com.caiotayota.findaroom.entities.Ad;
import com.caiotayota.findaroom.services.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/ads", produces = APPLICATION_JSON_VALUE)
public class AdController {

    private final AdService service;

    @Autowired
    public AdController(AdService service) {
        this.service = service;
    }

    @PostMapping(path = "/create", produces = APPLICATION_JSON_VALUE)
    public Ad createAd(@RequestBody @Valid Ad adToSave) {
        return service.createAd(adToSave);
    }

    @GetMapping
    public List<Ad> findProperties() {
        return service.findAds();
    }
}
