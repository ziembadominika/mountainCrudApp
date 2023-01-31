package com.crudApp.mountain.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COUNTRIES")

public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long id;

    @Column(name = "COUNTRY_NAME", nullable = false)
    private String countryName;

//    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "countries")
    @Transient
    private List<MountainRange> mountainRanges = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "continent_id")
    private Continent continent;

    public List<Mountain> getMountainsFromCountry(String countryName) {
        return mountainRanges.stream()
                .flatMap(mountainRange -> mountainRange.getMountains().stream())
                .filter(mountain -> mountain.getCountry().contains(countryName))
                .collect(Collectors.toList());
    }
}
