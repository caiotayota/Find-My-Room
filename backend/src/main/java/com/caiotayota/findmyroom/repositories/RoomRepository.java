package com.caiotayota.findmyroom.repositories;

import com.caiotayota.findmyroom.entities.Room;
import com.caiotayota.findmyroom.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByUser(User user);
    List<Room> findByStreetAddressContainingIgnoreCase(String streetAddress);

}
