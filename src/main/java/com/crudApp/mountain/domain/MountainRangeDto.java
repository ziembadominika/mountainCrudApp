package com.crudApp.mountain.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@AllArgsConstructor
@Getter
public class MountainRangeDto {

    private Long id;
    private String rangeName;
    private List<Mountain> mountains;
    
}
