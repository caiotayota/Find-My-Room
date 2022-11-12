package com.caiotayota.findmyroom.services;

import com.caiotayota.findmyroom.entities.Ad;
import com.caiotayota.findmyroom.entities.Room;
import com.caiotayota.findmyroom.entities.User;
import com.caiotayota.findmyroom.exceptions.RoomNotFoundException;
import com.caiotayota.findmyroom.exceptions.UserNotAllowedException;
import com.caiotayota.findmyroom.exceptions.UsernameExistsException;
import com.caiotayota.findmyroom.repositories.AdRepository;
import com.caiotayota.findmyroom.repositories.RoomRepository;
import com.caiotayota.findmyroom.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AdRepository adRepository;
    private final RoomRepository roomRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, AdRepository adRepository, RoomRepository roomRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = repository;
        this.adRepository = adRepository;
        this.roomRepository = roomRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> getUser(String email) {
        return userRepository.findById(email);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        Optional<User> userToSave = userRepository.findById(user.getEmail());
        if (userToSave.isPresent()) {
            throw new UsernameExistsException();
        } else {
            String encodedPassword = passwordEncoder.encode(user.getEncryptedPassword());
            user.setEncryptedPassword(encodedPassword);
            return userRepository.save(user);
        }
    }

    public User updateUser(String email, User updatedUser) {

        Optional<User> user = userRepository.findById(email);

        if (user.isEmpty()) throw new RoomNotFoundException();

        if (!user.get().getEmail().equals(getLoggedUser())) {
            throw new UserNotAllowedException();
        }

        String encodedPassword = passwordEncoder.encode(updatedUser.getEncryptedPassword());
        user.get().setEncryptedPassword(encodedPassword);

        user.get().setFirstName(updatedUser.getFirstName());
        user.get().setLastName(updatedUser.getLastName());
        user.get().setDateOfBirth(updatedUser.getDateOfBirth());
        user.get().setNationality(updatedUser.getNationality());
        user.get().setPhoneNo(updatedUser.getPhoneNo());
        user.get().setPpsNo(updatedUser.getPpsNo());

        return userRepository.save(user.get());
    }

    public void deleteUser(String email) {
        User user = userRepository.findById(email).orElseThrow();

        if (!email.equals(getLoggedUser())) {
            throw new UserNotAllowedException();
        }
        deleteAllUserAds(user);
        deleteAllUserRooms(user);
        userRepository.deleteById(email);
    }

    public String getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getPrincipal().toString();
    }

    public void deleteAllUserAds(User user) {
        List<Ad> ads = adRepository.findByUser(user);
        for (Ad ad : ads) {
            adRepository.deleteById(ad.getId());
        }
    }

    public void deleteAllUserRooms(User user) {
        List<Room> rooms = roomRepository.findByUser(user);
        for (Room room : rooms) {
            roomRepository.deleteById(room.getId());
        }
    }
}
