package com.crudApp.mountain.domain;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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
    private List<Mountain> mountains;
    private String password;
    private List<Role>roles;

    public UserDto(Long id, String userName, String firstName, String lastName, int yearOfBirth, int monthOfBirth,
                   int dayOfBirth, String email, int yearOfRegistration, int monthOfRegistration, int dayOfRegistration,
                   List<UserRating> userRatings, List<Mountain> mountains, String password, List<Role>roles) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth);
        this.email = email;
        this.dateOfRegistration = LocalDate.of(yearOfRegistration, monthOfRegistration, dayOfRegistration);
        this.userRatings = userRatings;
        this.mountains = mountains;
        this.password = password;
        this.roles = roles;
    }


}
