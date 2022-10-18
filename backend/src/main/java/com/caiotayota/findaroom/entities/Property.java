package com.caiotayota.findaroom.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "properties")
@Getter
@Setter
@NoArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_id")
    private long id;

    private String streetAddress;
    private String eirCode;
    private boolean washingMachine;
    private boolean dryer;
    private boolean dishWasher;
    private boolean petAllowed;
    private boolean parking;

}
