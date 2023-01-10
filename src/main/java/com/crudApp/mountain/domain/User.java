package com.crudApp.mountain.domain;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "USERS")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @Column(name= "USER_NAME")
    @NotNull
    private String userName;

    @Column(name= "FIRST_NAME")
    @NotNull
    private String firstName;

    @Column(name= "LAST_NAME")
    @NotNull
    private String lastName;

    @Column(name="DATE_OF_BIRTH")
    private LocalDate birthDate;

    @NotNull
    private String email;

    @Column(name = "DATE_OF_REGISTRATION")
    @NotNull
    private LocalDate dateOfRegistration;


    public User(Long id, String userName, String firstName, String lastName, int yearOfBirth, int monthOfBirth,
                int dayOfBirth, String email, int yearOfRegistration, int monthOfRegistration, int dayOfRegistration) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth);
        this.email = email;
        this.dateOfRegistration = LocalDate.of(yearOfRegistration, monthOfRegistration, dayOfRegistration);
    }
}

