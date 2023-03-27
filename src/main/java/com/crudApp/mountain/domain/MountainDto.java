package com.crudApp.mountain.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MountainDto {
    private Long id;
    private String mountainName;
    private int height;
    private MountainRangeDto mountainRange;
    private String country;
    private String continent;
    private List<UserRatingDto> usersRatings;
}

