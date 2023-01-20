package com.crudApp.mountain.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
public class CountryDto {

    private long id;
    private String countryName;
    private List<MountainRange> mountainRanges;

    public CountryDto(long id, String countryName) {
        this.id = id;
        this.countryName = countryName;
    }
}
