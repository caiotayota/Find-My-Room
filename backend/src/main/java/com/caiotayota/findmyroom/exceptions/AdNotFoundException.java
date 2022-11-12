package com.caiotayota.findmyroom.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AdNotFoundException extends RuntimeException {

    public AdNotFoundException() {
        super("NOT FOUND: Ad doesn't exist!");
    }
}
