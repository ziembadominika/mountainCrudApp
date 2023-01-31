package com.crudApp.mountain.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
public class CountryDto {

    private long id;
    private String countryName;
    private List<MountainRange> mountainRanges;
    private Continent continent;

}
