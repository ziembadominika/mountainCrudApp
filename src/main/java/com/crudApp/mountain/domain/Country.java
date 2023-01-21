package com.crudApp.mountain.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "COUNTRIES")
@NoArgsConstructor
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long id;

    @Column(name = "COUNTRY_NAME", nullable = false)
    private String countryName;

    @Transient
    private List<MountainRange> mountainRanges = new ArrayList<>();

    public Country(long id, String countryName) {
        this.id = id;
        this.countryName = countryName;
    }

    @ManyToOne
    @JoinColumn(name = "continent_id")
    Continent continent;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "countries")
    public List<MountainRange> getMountainRanges() {
        return mountainRanges;
    }

    public List<Mountain> getMountainsFromCountry(String countryName) {
        return mountainRanges.stream()
                .flatMap(mountainRange -> mountainRange.getMountains().stream())
                .filter(mountain -> mountain.getCountry().contains(countryName))
                .collect(Collectors.toList());
    }
}
