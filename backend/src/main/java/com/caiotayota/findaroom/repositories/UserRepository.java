package com.caiotayota.findaroom.repositories;

import com.caiotayota.findaroom.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
