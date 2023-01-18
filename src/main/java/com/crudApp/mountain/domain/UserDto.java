package com.crudApp.mountain.domain;

import com.crudApp.mountain.deserializer.DateHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import java.time.LocalDate;

@Data
public class UserDto {
    Long id;
    String userName;
    String firstName;
    String lastName;
    LocalDate birthDate;
    String email;
    LocalDate dateOfRegistration;

    public UserDto(Long id, String userName, String firstName, String lastName, int yearOfBirth, int monthOfBirth,
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
