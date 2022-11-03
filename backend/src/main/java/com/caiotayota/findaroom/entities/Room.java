package com.caiotayota.findaroom.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private long id;

    private String streetAddress;
    private String eirCode;
    private String roomType;
    private boolean ensuiteBathroom;
    private boolean heating;
    private boolean carpeted;

    @OneToOne
    @JoinColumn(name = "lessor")
    private User user;

}
