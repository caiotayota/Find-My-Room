package com.caiotayota.findmyroom.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AdDto {

    // ad
    private BigDecimal rent;
    private boolean billsIncluded;
    private boolean owner_occupied;
    private boolean parking;
    private boolean petAllowed;
    private boolean washingMachine;
    private boolean dryer;
    private boolean dishWasher;


    // room
    private String streetAddress;
    private String eirCode;
    private String roomType;
    private boolean ensuiteBathroom;
    private boolean heating;
    private boolean carpeted;
    private long roomImageId;

}
