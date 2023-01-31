package com.crudApp.mountain.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.util.List;

@Getter
@Setter
public class ContinentDto {
    private long id;
    private String continentName;
    private List<Country> countries;

    public ContinentDto(long id, String continentName) {
        this.id = id;
        this.continentName = continentName;
    }
}
