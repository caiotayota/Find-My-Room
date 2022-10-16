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
    @Column(name = "property_id")
    private long id;

    @OneToMany(mappedBy = "property")
    private List<Room> rooms = new ArrayList<>();

    @OneToMany(mappedBy = "property")
    private List<Ad> ads = new ArrayList<>();

    String streetAddress;
    String eirCode;
    boolean washingMachine;
    boolean dryer;
    boolean dishWasher;

}
