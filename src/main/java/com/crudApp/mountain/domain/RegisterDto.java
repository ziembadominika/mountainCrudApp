package com.crudApp.mountain.domain;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class RegisterDto {
    private String userName;
    private char[] password;
    private String firstName;
    private String lastName;
    private String email;
}
