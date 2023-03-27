package com.crudApp.mountain.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRatingDto {

    private Long id;
    private UserEntityDto userEntityDto;
    private int rate;
    private MountainDto mountainDto;
}
