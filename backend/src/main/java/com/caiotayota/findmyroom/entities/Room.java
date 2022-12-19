package com.caiotayota.findmyroom.entities;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rooms")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roomId")
    private long id;

    private String streetAddress;
    private String eirCode;
    private String roomType;
    private boolean ensuiteBathroom;
    private boolean heating;
    private boolean carpeted;

    @OneToOne
    @JoinColumn(name = "roomImage")
    private RoomImage roomImage;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;


}
