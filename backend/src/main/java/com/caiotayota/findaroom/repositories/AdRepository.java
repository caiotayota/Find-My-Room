package com.caiotayota.findaroom.repositories;

import com.caiotayota.findaroom.entities.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepository extends JpaRepository<Ad, Long> {
}
