package com.crudApp.mountain.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "COUNTRY")
@NoArgsConstructor
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long id;

    @Column(name = "COUNTRY_NAME", nullable=false)
    private String countryName;

    @Transient
    private List<MountainRange>mountainRanges;



    @ManyToMany
    public List<MountainRange> getMountainRanges() {
        return mountainRanges;
    }

    public List<Mountain> getMountainsFromCountry(String countryName){
        return mountainRanges.stream()
                .flatMap(mountainRange -> mountainRange.getMountainsFromRange(mountainRange.getRangeName()).stream())
                .filter(mountain -> mountain.getCountry().contains(countryName))
                .collect(Collectors.toList());
    }
}
