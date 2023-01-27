package com.crudApp.mountain.domain;

import com.crudApp.mountain.deserializer.DateHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "USERS")
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @Column(name = "USER_NAME")
    @NotNull
    private String userName;

    @Column(name = "FIRST_NAME")
    @NotNull
    private String firstName;

    @Column(name = "LAST_NAME")
    @NotNull
    private String lastName;

    @Column(name = "DATE_OF_BIRTH")
    @JsonDeserialize(using = DateHandler.class)
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate birthDate;

    @NotNull
    private String email;

    @Column(name = "DATE_OF_REGISTRATION")
    @NotNull
    @JsonDeserialize(using = DateHandler.class)
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate dateOfRegistration;

    @OneToMany
    @JoinColumn(name = "USER_ID")
    private List<UserRating> userRatings;


    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "USERS_userMountains",
            joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "userMountains_id", referencedColumnName = "id"))
    private List<Mountain> userMountains;

    public User(Long id, String userName, String firstName, String lastName, int yearOfBirth, int monthOfBirth,
                int dayOfBirth, String email, int yearOfRegistration, int monthOfRegistration, int dayOfRegistration,
                List<UserRating> userRatings, List<Mountain> userMountains) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth);
        this.email = email;
        this.dateOfRegistration = LocalDate.of(yearOfRegistration, monthOfRegistration, dayOfRegistration);
        this.userRatings = userRatings;
        this.userMountains = userMountains;
    }
}

