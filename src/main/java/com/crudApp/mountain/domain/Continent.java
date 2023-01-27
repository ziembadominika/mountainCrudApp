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
@Table(name = "CONTINENTS")
@NoArgsConstructor
public class Continent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @Column(name = "CONTINENT_NAME")
    private String continentName;
    @Transient
    private List<Country>countries;

    public Continent(long id, String continentName) {
        this.id = id;
        this.continentName = continentName;
    }

    @OneToMany(
    targetEntity = Country.class,
    mappedBy = "continent",
    cascade = CascadeType.ALL,
    fetch = FetchType.LAZY)
    public List<Country> getCountries() {
        return countries;
    }

    public List<Mountain> getMountainFromContinent(String continentName){
        return countries.stream()
                .flatMap(country-> country.getMountainsFromCountry(country.getCountryName()).stream())
                .collect(Collectors.toList());
    }
}
