package com.caiotayota.findmyroom.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserNotAllowedException extends RuntimeException{

    public UserNotAllowedException() {
        super("FORBIDDEN: You are not allowed to delete the ad!");
    }
}