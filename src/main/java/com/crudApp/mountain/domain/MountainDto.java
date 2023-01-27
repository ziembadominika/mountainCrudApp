package com.crudApp.mountain.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MountainDto {
    private long id;
    private String name;
    private int height;
    private String country;
    private MountainRange mountainRange;
    private List<UserRating> userRatings;
    private List<User> users;
}

