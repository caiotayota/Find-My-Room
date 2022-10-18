package com.caiotayota.findaroom.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameExistsException extends RuntimeException {

    public UsernameExistsException() {
        super("BAD REQUEST: Username already exists, try a new one!");
    }
}