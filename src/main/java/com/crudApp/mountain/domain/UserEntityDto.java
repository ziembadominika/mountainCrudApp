package com.crudApp.mountain.domain;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserEntityDto {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private List<UserRating> userRatings;
    private List<Mountain> mountains;
    private String password;
    private List<Role>roles;

    public UserEntityDto(Long id, String userName, String firstName, String lastName, String email, List<UserRating> userRatings,
                         List<Mountain> mountains, String password, List<Role>roles) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userRatings = userRatings;
        this.mountains = mountains;
        this.password = password;
        this.roles = roles;
    }
}
