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
public class MountainRange {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long id;

    @Column(name = "RANGE_NAME")
    private String rangeName;

    @Transient
    private List<Mountain> mountains = new ArrayList<>();

    public MountainRange(long id, String rangeName, List<Mountain> mountains) {
        this.id = id;
        this.rangeName = rangeName;
        this.mountains = mountains;
    }

    @OneToMany(
            targetEntity = Mountain.class,
            mappedBy = "rangeId",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    public List<Mountain> getMountains() {
        return mountains;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "JOIN_COUNTRY_RANGE",
            joinColumns = {@JoinColumn(name = "RANGE_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "COUNTRY_ID", referencedColumnName = "ID")}
    )
    private List<Country> countries;
}

