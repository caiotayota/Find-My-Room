package com.caiotayota.findmyroom.repositories;

import com.caiotayota.findmyroom.entities.RoomImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomImageRepository extends JpaRepository<RoomImage,Long> {
    Optional<RoomImage> findById(Long id);
}