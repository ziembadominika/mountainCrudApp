package com.crudApp.mountain.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "MOUNTAIN_RANGE")
@NoArgsConstructor
@AllArgsConstructor
public class MountainRange {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @Column(name = "RANGE_NAME")
    private String rangeName;

    @OneToMany(
            mappedBy = "mountainRange",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Mountain> mountains = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "JOIN_COUNTRY_RANGE",
            joinColumns = {@JoinColumn(name = "RANGE_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "COUNTRY_ID", referencedColumnName = "id")}
    )
    private List<Country> countries;

}

