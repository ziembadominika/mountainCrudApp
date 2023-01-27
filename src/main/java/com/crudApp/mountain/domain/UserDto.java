package com.crudApp.mountain.domain;

import com.crudApp.mountain.deserializer.DateHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private LocalDate dateOfRegistration;

    private List<UserRating> userRatings;

    private List<Mountain> userMountains;

    public UserDto(Long id, String userName, String firstName, String lastName, int yearOfBirth, int monthOfBirth,
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
