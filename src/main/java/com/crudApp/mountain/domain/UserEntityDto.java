package com.crudApp.mountain.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

}
