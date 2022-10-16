package com.caiotayota.findaroom.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "user_preferences")
@NoArgsConstructor
@AllArgsConstructor
public class UserPreferences {

    @Id
    @Column(name = "email", nullable = false)
    @Email(regexp = ".+@.+\\..+", message = "Email is not valid.")
    @NotBlank
    private String email;

    private String roomType;
    private boolean parking;
    private int minPrice;
    private int maxPrice;

}
