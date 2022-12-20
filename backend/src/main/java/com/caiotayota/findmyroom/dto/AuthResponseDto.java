package com.caiotayota.findmyroom.dto;

import com.caiotayota.findmyroom.entities.Role;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AuthResponseDto {

    private String email;
    private String tokenType = "Bearer ";
    private String accessToken;
    private List<String> roles = new ArrayList<>();

    public AuthResponseDto(String email, String accessToken, List<Role> roles) {
        this.accessToken = accessToken;
        this.email = email;
        roles.stream().forEach(role -> this.roles.add(role.getName()));
    }
}
