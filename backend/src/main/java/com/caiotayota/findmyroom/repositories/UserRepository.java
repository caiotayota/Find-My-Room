package com.caiotayota.findmyroom.repositories;

import com.caiotayota.findmyroom.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
