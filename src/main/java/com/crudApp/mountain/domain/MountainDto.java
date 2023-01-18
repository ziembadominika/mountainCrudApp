package com.crudApp.mountain.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class MountainDto {
    private long id;
    private String name;
    private int height;
    private String country;
    private MountainRange mountainRange;

    public MountainDto(long id, String name, int height, String country) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.country = country;
    }
}
