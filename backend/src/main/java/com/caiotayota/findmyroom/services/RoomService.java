package com.caiotayota.findmyroom.services;

import com.caiotayota.findmyroom.entities.Room;
import com.caiotayota.findmyroom.exceptions.RoomNotFoundException;
import com.caiotayota.findmyroom.repositories.RoomRepository;
import com.caiotayota.findmyroom.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository, UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    public Optional<Room> getRoom(long id) {
        return roomRepository.findById(id);
    }

    public List<Room> findRooms() {
        return roomRepository.findAll();
    }

    public List<Room> getRoomsByAddress(String streetAddress) {
        List<Room> rooms = roomRepository.findByStreetAddressContainingIgnoreCase(streetAddress);
        return rooms != null ? rooms : Collections.emptyList();
    }

    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(long id, Room updatedRoom) {

        Optional<Room> room = roomRepository.findById(id);

        if (room.isEmpty()) throw new RoomNotFoundException();

        room.get().setStreetAddress(updatedRoom.getStreetAddress());
        room.get().setEirCode(updatedRoom.getEirCode());
        room.get().setRoomType(updatedRoom.getRoomType());
        room.get().setCarpeted(updatedRoom.isCarpeted());
        room.get().setHeating(updatedRoom.isHeating());
        room.get().setEnsuiteBathroom(updatedRoom.isEnsuiteBathroom());
        return roomRepository.save(room.get());
    }

    public void deleteRoom(long id) {
        Optional<Room> room = roomRepository.findById(id);
        if (room.isEmpty()) throw new RoomNotFoundException();
        roomRepository.deleteById(id);
    }

    public String getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getPrincipal().toString();
    }
}
