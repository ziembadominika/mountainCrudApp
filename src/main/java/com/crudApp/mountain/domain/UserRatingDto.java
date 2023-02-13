package com.crudApp.mountain.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRatingDto {

    private Long id;
    private UserEntity userEntity;
    private int rate;
    private Mountain mountain;
}
