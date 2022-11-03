package com.caiotayota.findaroom.services;

import com.caiotayota.findaroom.entities.Ad;
import com.caiotayota.findaroom.entities.User;
import com.caiotayota.findaroom.exceptions.AdNotFoundException;
import com.caiotayota.findaroom.exceptions.UserNotAllowedException;
import com.caiotayota.findaroom.repositories.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AdService {

    private final AdRepository repository;

    @Autowired
    public AdService(AdRepository repository) {
        this.repository = repository;
    }

    public String getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getPrincipal().toString();
    }

    public List<Ad> getAds() {
        return repository.findAll();
    }

    public Optional<Ad> getAd(long id) {
        return repository.findById(id);
    }

    public List<Ad> getAdByUser(User user) {
        List<Ad> properties = repository.findByUser(user);
        return properties != null ? properties : Collections.emptyList();
    }

    public List<Ad> getAdByParking(boolean parking) {
        List<Ad> properties = repository.findByParking(parking);
        return properties != null ? properties : Collections.emptyList();
    }

    public Ad createAd(Ad ad) {
        return repository.save(ad);
    }

    public void updateAd(long id, Ad updatedAd) {
        Optional<Ad> ad = repository.findById(id);

        if (ad.isEmpty()) throw new AdNotFoundException();

        if (!ad.get().getUser().getEmail().equals(getLoggedUser())) {
            throw new UserNotAllowedException();
        }

        ad.get().setOwner_occupied(updatedAd.isOwner_occupied());
        ad.get().setRent(updatedAd.getRent());
        repository.save(ad.get());
    }

    public void deleteAd(long id) {
        Optional<Ad> ad = repository.findById(id);
        if (ad.isEmpty()) throw new AdNotFoundException();

        if (!ad.get().getUser().getEmail().equals(getLoggedUser())) {
            throw new UserNotAllowedException();
        }
        repository.deleteById(id);
    }
}
