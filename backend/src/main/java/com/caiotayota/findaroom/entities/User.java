package com.caiotayota.findaroom.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "email", nullable = false)
    @Email(regexp = ".+@.+\\..+", message = "Email is not valid.")
    @NotBlank
    private String email;

    @OneToOne
    @JoinColumn(name = "email")
    private UserPreferences userPreferences;

    @Size(min = 8, message = "The password must have at least 8 characters.")
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String dateOfBirth;
    private String nationality;
    private String phoneNo;
    private String ppsNo;

}
