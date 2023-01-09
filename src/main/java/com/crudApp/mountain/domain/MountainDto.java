package com.crudApp.mountain.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MountainDto {
    private long id;
    private String name;
    private int height;
    private String country;
    private String mountainRange;

}
