package com.caiotayota.findmyroom.config;

import com.caiotayota.findmyroom.entities.User;
import com.caiotayota.findmyroom.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         User user = userRepository.findUserByEmail(email);

         if (user == null) {
             throw new UsernameNotFoundException("User not found: " + email);
         }

        return user;
    }
}
