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
    private List<UserRatingDto> userRatings;
    private List<MountainDto> mountains;
    private String password;
    private List<Role>roles;

    public UserEntityDto(Long id, String userName, String firstName, String lastName, String email,
                         List<UserRatingDto> userRatings, List<MountainDto> mountains) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userRatings = userRatings;
        this.mountains = mountains;
    }
}
