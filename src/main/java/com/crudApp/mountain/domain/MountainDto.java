package com.crudApp.mountain.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
public class MountainDto {
    private Long id;
    private String name;
    private int height;
    private MountainRange mountainRange;
    private String country;
    private String continent;
    private List<UserRating> userRatings;
    private Set<User> users;
}

