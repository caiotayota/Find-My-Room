package com.caiotayota.findaroom.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException() {
        super("NOT FOUND: Room doesn't exist!");
    }
}
