package com.caiotayota.findmyroom.services;

import com.caiotayota.findmyroom.dto.AdDto;
import com.caiotayota.findmyroom.entities.Ad;
import com.caiotayota.findmyroom.entities.Room;
import com.caiotayota.findmyroom.entities.RoomImage;
import com.caiotayota.findmyroom.entities.User;
import com.caiotayota.findmyroom.exceptions.AdNotFoundException;
import com.caiotayota.findmyroom.exceptions.UserNotAllowedException;
import com.caiotayota.findmyroom.repositories.AdRepository;
import com.caiotayota.findmyroom.repositories.RoomImageRepository;
import com.caiotayota.findmyroom.repositories.RoomRepository;
import com.caiotayota.findmyroom.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdService {

    private final AdRepository adRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final RoomImageRepository roomImageRepository;

    @Autowired
    public AdService(AdRepository adRepository, RoomRepository roomRepository, UserRepository userRepository, RoomImageRepository roomImageRepository) {
        this.adRepository = adRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.roomImageRepository = roomImageRepository;
    }

    public Optional<Ad> getAd(long id) {
        return adRepository.findById(id);
    }

    public List<Ad> getAds() {
        return adRepository.findAds();
    }

    public List<Ad> getAdsByUser(User user) {
        List<Ad> ads = adRepository.findByUser(user);
        return ads != null ? ads : Collections.emptyList();
    }

    public List<Ad> getAdsByParking(boolean parking) {
        List<Ad> ads = adRepository.findByParking(parking);
        return ads != null ? ads : Collections.emptyList();
    }

    public Ad createAd(AdDto adDto) {

        Room room = new Room();
        room.setRoomType(adDto.getRoomType());
        room.setStreetAddress(adDto.getStreetAddress());
        room.setEirCode(adDto.getEirCode());
        room.setEnsuiteBathroom(adDto.isEnsuiteBathroom());
        room.setHeating(adDto.isHeating());
        room.setCarpeted(adDto.isCarpeted());

        Optional<RoomImage> roomImage = roomImageRepository.findById(adDto.getRoomImageId());
        roomImage.ifPresent(room::setRoomImage);
        room.setCreatedAt(new Date());

        Ad ad = new Ad();
        ad.setRoom(roomRepository.save(room));
        ad.setRent(adDto.getRent());
        ad.setBillsIncluded(adDto.isBillsIncluded());
        ad.setOwnerOccupied(adDto.isOwner_occupied());
        ad.setParking(adDto.isParking());
        ad.setPetAllowed(adDto.isPetAllowed());
        ad.setWashingMachine(adDto.isWashingMachine());
        ad.setDryer(adDto.isDryer());
        ad.setDishWasher(ad.isDishWasher());

        ad.setUser(userRepository.findUserByEmail(getLoggedUser()).orElseThrow());

        ad.setCreatedAt(new Date());
        return adRepository.save(ad);
    }

    public Ad updateAd(long id, Ad updatedAd) {
        Optional<Ad> ad = adRepository.findById(id);

        if (ad.isEmpty()) throw new AdNotFoundException();

        if (!ad.get().getUser().getEmail().equals(getLoggedUser())) {
            throw new UserNotAllowedException();
        }

        ad.get().setOwnerOccupied(updatedAd.isOwnerOccupied());
        ad.get().setRent(updatedAd.getRent());
        ad.get().setUpdatedAt(new Date());
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
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }

    }
}
