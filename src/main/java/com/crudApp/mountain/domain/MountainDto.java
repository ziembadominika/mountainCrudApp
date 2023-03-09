package com.crudApp.mountain.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class MountainDto {
    private Long id;
    private String name;
    private int height;
    private MountainRange mountainRange;
    private String country;
    private String continent;
    private List<UserRating> usersRatings;
}

