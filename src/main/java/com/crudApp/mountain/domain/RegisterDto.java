package com.crudApp.mountain.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterDto {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
}
