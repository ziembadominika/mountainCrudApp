package com.crudApp.mountain.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class MountainRangeDto {

    private Long id;
    private String rangeName;
    private List<Mountain> mountains;
    private List<Country> countries;


}
