package com.caiotayota.findmyroom.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@Entity @Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(regexp = ".+@.+\\..+", message = "Email is not valid.")
    @NotBlank
    private String email;

    @Size(min = 8, message = "Password must have at least 8 characters.")
    private String password;

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String nationality;
    private String phoneNo;
    private String ppsNo;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();

    private boolean verifiedEmail;
    private String verificationCode;
    @Temporal(TemporalType.TIMESTAMP)
    private Date verificationCodeSentAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public User() {
        this.verifiedEmail = false;
    }
}

