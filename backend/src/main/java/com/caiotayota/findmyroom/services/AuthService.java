package com.caiotayota.findmyroom.services;

import com.caiotayota.findmyroom.dto.AuthResponseDto;
import com.caiotayota.findmyroom.dto.LoginDto;
import com.caiotayota.findmyroom.dto.RegisterDto;
import com.caiotayota.findmyroom.entities.Role;
import com.caiotayota.findmyroom.entities.User;
import com.caiotayota.findmyroom.repositories.RoleRepository;
import com.caiotayota.findmyroom.repositories.UserRepository;
import com.caiotayota.findmyroom.security.JwtGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service @Slf4j
public class AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtGenerator jwtGenerator;
    private final EmailService emailService;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository,
                       RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtGenerator jwtGenerator, EmailService emailService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
        this.emailService = emailService;
    }

    public ResponseEntity<AuthResponseDto> login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);

        List<Role> roles = userRepository.findUserByEmail(loginDto.getEmail()).get().getRoles();

        return new ResponseEntity<>(new AuthResponseDto(token, roles), HttpStatus.OK);
    }

    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            return new ResponseEntity<>(registerDto.getEmail() + " is already registered!", HttpStatus.CONFLICT);
        }

        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());

        Role roles = roleRepository.findByName("USER").orElseThrow();
        user.setRoles(Collections.singletonList(roles));

        String verificationCode = emailService.generateVerificationCode();
        user.setVerificationCode(verificationCode);

        user.setCreatedAt(new Date());
        userRepository.save(user);

        String verificationCodeMsg = String.format("Dear %s,%n%n" +
                "The verification code to complete your registration on Find My Room platform is: %s", user.getFirstName(), verificationCode);

        Map<String, Object> propertiesMap = new HashMap<>();
        propertiesMap.put("name", user.getFirstName());
        propertiesMap.put("message", verificationCodeMsg);
            emailService.sendEmailTemplate(user.getEmail(), "Register on Find My Room", propertiesMap);

        log.info("User {} saved on data base", user.getEmail());
        return new ResponseEntity<>("User " + user.getEmail() + " was successfully registered!", HttpStatus.OK);
    }

}
