package com.caiotayota.findaroom.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ads")
@Getter
@Setter
@NoArgsConstructor
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_id")
    private long id;

    @OneToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "lessor")
    private User user;

    private BigDecimal rent;
    private boolean billsIncluded;
    private boolean owner_occupied;
    private boolean parking;
    private boolean petAllowed;
    private boolean washingMachine;
    private boolean dryer;
    private boolean dishWasher;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    Date date;

}
