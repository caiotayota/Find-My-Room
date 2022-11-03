package com.caiotayota.findaroom.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    public User(String email, String encryptedPassword, String firstName, String lastName) {
        this.email = email;
        this.encryptedPassword = encryptedPassword;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Id
    @Column(name = "email", nullable = false)
    @Email(regexp = ".+@.+\\..+", message = "Email is not valid.")
    @NotBlank
    private String email;

    @Size(min = 8, message = "The password must have at least 8 characters.")
    private String encryptedPassword;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String dateOfBirth;
    private String nationality;
    private String phoneNo;
    private String ppsNo;

}
