package com.caiotayota.findmyroom.dto;

import lombok.Data;

@Data
public class EmailVerificationDto {
    private String email;
    private String verificationCode;
}
