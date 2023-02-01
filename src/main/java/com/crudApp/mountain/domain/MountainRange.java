package com.crudApp.mountain.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "MOUNTAIN_RANGE")
@NoArgsConstructor
@AllArgsConstructor
public class MountainRange {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "RANGE_NAME")
    private String rangeName;

    @OneToMany(
            mappedBy = "mountainRange",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Mountain> mountains = new ArrayList<>();

}

