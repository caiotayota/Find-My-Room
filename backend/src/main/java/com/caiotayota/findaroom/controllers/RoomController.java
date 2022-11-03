package com.caiotayota.findaroom.controlers;

import com.caiotayota.findaroom.entities.Ad;
import com.caiotayota.findaroom.entities.Room;
import com.caiotayota.findaroom.entities.User;
import com.caiotayota.findaroom.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoomController {

    private final RoomService service;

    @Autowired
    public RoomController(RoomService service) {
        this.service = service;
    }

    @GetMapping
    public List<Room> findRooms() {
        return service.findRooms();
    }

    @GetMapping("/{id}")
    public Optional<Room> getRoom(@PathVariable long id) {
        return service.getRoom(id);
    }

    @GetMapping(value = "/search/", params = "streetAddress")
    public List<Room> getRoomsByAddress(@RequestParam @Valid String streetAddress) {
        return service.getRoomsByAddress(streetAddress);
    }

    @GetMapping(value = "/search/", params = "user")
    public List<Room> getRoomsByUser(@RequestParam @Valid User user) {
        return service.getRoomsByUser(user);
    }

    @PostMapping(path = "/new", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Room createRoom(@RequestBody @Valid Room room) {
        return service.createRoom(room);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Room updateRoom(@PathVariable long id, @RequestBody @Valid Room updatedRoom) {
        return service.updateRoom(id, updatedRoom);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoom(@PathVariable long id) {
        service.deleteRoom(id);
    }
}
