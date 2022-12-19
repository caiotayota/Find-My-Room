package com.caiotayota.findmyroom.services;

import com.caiotayota.findmyroom.entities.Ad;
import com.caiotayota.findmyroom.entities.User;
import com.caiotayota.findmyroom.exceptions.UserNotAllowedException;
import com.caiotayota.findmyroom.repositories.AdRepository;
import com.caiotayota.findmyroom.repositories.RoleRepository;
import com.caiotayota.findmyroom.repositories.RoomRepository;
import com.caiotayota.findmyroom.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service @Transactional @Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final AdRepository adRepository;
    private final RoomRepository roomRepository;
    private final RoleRepository roleRepository;
    private final EmailService emailService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, AdRepository adRepository, RoomRepository roomRepository, RoleRepository roleRepository, EmailService emailService, PasswordEncoder passwordEncoder) {
        this.userRepository = repository;
        this.adRepository = adRepository;
        this.roomRepository = roomRepository;
        this.roleRepository = roleRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> getUser(Long userId) {
        return userRepository.findById(userId);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity<String> verifyEmail(String email, String verificationCode) {
        User user = userRepository.findUserByEmail(email).orElseThrow();

        if (user.getVerificationCode().equals(verificationCode)) {
            user.setVerifiedEmail(true);
            return new ResponseEntity<>("The email was successfully verified!" , HttpStatus.ACCEPTED);
        } else {
            System.out.println("Invalid verification code!");
            return new ResponseEntity<>("Invalid Code!", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public String changePassword(User user) {

        String verificationCode = emailService.requestVerificationCode(user.getEmail());
        String verificationCodeMsg = String.format("Dear %s,%n%n" +
                "Your  verification code is %s", user.getEmail(), verificationCode);
        emailService.sendEmail(user.getEmail(), "Verification Code", verificationCodeMsg);

        User userDb = userRepository.findByEmailAndVerificationCode(user.getEmail(), verificationCode);

        if (userDb != null) {
            Date difference = new Date(new Date().getTime() - userDb.getVerificationCodeSentAt().getTime());
            if (difference.getTime() / 1000 < 900) {
                String encodedPassword = passwordEncoder.encode(user.getPassword());
                userDb.setPassword(encodedPassword);
            } else {
                return "Time-out: Verification code is expired, request another one.";
            }
        } else {
            throw new UsernameNotFoundException("User " + user.getEmail() + " doesn't exist");
        }
        return "The password was changed - username: " + user.getEmail();
    }

    public User updateUser(User updatedUser) {
        User user = new User();

        if (userRepository.existsByEmail(updatedUser.getEmail())) {
            if (user.getEmail().equals(getLoggedUser())) {
                user.setFirstName(updatedUser.getFirstName().length() > 0 ? updatedUser.getFirstName() : user.getFirstName());
                user.setLastName(updatedUser.getLastName().length() > 0 ? updatedUser.getLastName() : user.getLastName());
                user.setDateOfBirth(updatedUser.getDateOfBirth().length() > 0 ? updatedUser.getDateOfBirth() : user.getDateOfBirth());
                user.setPhoneNo(updatedUser.getPhoneNo().length() > 0 ? updatedUser.getPhoneNo() : user.getPhoneNo());
                user.setPpsNo(updatedUser.getPpsNo().length() > 0 ? updatedUser.getPpsNo() : user.getPpsNo());
            } else {
                throw new UserNotAllowedException();
            }
        } else {
            throw new UsernameNotFoundException("User " + user.getEmail() + " doesn't exist");
        }
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow();

        if (!user.getEmail().equals(getLoggedUser())) {
            throw new UserNotAllowedException();
        }
        deleteAllUserAds(user);
        userRepository.deleteById(id);
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
}
