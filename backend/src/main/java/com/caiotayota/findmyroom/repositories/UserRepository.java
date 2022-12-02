package com.caiotayota.findmyroom.repositories;

import com.caiotayota.findmyroom.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
    Boolean existsByEmail(String email);
    User findByEmailAndVerificationCode(String email, String verificationCode);
}
