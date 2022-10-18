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

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    private String roomType;
    private boolean ensuiteBathroom;

    @OneToOne
    @JoinColumn(name = "lessor")
    private User lessor;
}
