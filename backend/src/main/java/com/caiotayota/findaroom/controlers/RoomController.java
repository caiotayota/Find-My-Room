package com.caiotayota.findaroom.controlers;

import com.caiotayota.findaroom.entities.Room;
import com.caiotayota.findaroom.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoomController {

    private final RoomService service;

    @Autowired
    public RoomController(RoomService service) {
        this.service = service;
    }

    @GetMapping
    public List<Room> findProperties() {
        return service.findRooms();
    }
}
