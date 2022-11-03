package com.caiotayota.findaroom.services;

import com.caiotayota.findaroom.entities.Ad;
import com.caiotayota.findaroom.entities.User;
import com.caiotayota.findaroom.exceptions.AdNotFoundException;
import com.caiotayota.findaroom.exceptions.UserNotAllowedException;
import com.caiotayota.findaroom.repositories.AdRepository;
import com.caiotayota.findaroom.repositories.RoomRepository;
import com.caiotayota.findaroom.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AdService {

    private final AdRepository adRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    @Autowired
    public AdService(AdRepository adRepository, RoomRepository roomRepository, UserRepository userRepository) {
        this.adRepository = adRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    public Optional<Ad> getAd(long id) {
        return adRepository.findById(id);
    }

    public List<Ad> getAds() {
        return adRepository.findAll();
    }

    public List<Ad> getAdsByUser(User user) {
        List<Ad> ads = adRepository.findByUser(user);
        return ads != null ? ads : Collections.emptyList();
    }

    public List<Ad> getAdsByParking(boolean parking) {
        List<Ad> ads = adRepository.findByParking(parking);
        return ads != null ? ads : Collections.emptyList();
    }

    public Ad createAd(Ad ad, long roomId) {

        if (!roomRepository.findById(roomId).get().getUser().getEmail().equals(getLoggedUser())) {
            throw new UserNotAllowedException();
        }

        ad.setRoom(roomRepository.findById(roomId).get());
        ad.setUser(userRepository.findById(getLoggedUser()).get());
        return adRepository.save(ad);
    }

    public Ad updateAd(long id, Ad updatedAd) {
        Optional<Ad> ad = adRepository.findById(id);

        if (ad.isEmpty()) throw new AdNotFoundException();

        if (!ad.get().getUser().getEmail().equals(getLoggedUser())) {
            throw new UserNotAllowedException();
        }

        ad.get().setOwner_occupied(updatedAd.isOwner_occupied());
        ad.get().setRent(updatedAd.getRent());
        return adRepository.save(ad.get());
    }

    public void deleteAd(long id) {
        Optional<Ad> ad = adRepository.findById(id);
        if (ad.isEmpty()) throw new AdNotFoundException();

        if (!ad.get().getUser().getEmail().equals(getLoggedUser())) {
            throw new UserNotAllowedException();
        }
        adRepository.deleteById(id);
    }

    public String getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getPrincipal().toString();
    }
}
