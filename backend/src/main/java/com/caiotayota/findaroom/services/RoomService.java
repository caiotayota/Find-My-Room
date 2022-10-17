package com.caiotayota.findaroom.services;

import com.caiotayota.findaroom.entities.Room;
import com.caiotayota.findaroom.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository repository;

    @Autowired
    public RoomService(RoomRepository repository) {
        this.repository = repository;
    }

    public List<Room> findRooms() {
        return repository.findAll();
    }
}
