package com.crudApp.mountain.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
public class UserRatingDto {

    private Long id;
    private User user;
    private int rate;
    private Long mountainId;
}
