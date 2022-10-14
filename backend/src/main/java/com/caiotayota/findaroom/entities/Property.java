package com.caiotayota.findaroom.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "properties")
@Getter
@Setter
@NoArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany
    @JoinColumn(name = "property_id", nullable = false)
    private List<Room> rooms = new ArrayList<>();

    String streetAddress;
    String eirCode;
    boolean washingMachine;
    boolean dryer;
    boolean dishWasher;

}
