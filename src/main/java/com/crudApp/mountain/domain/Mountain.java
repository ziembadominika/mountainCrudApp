package com.crudApp.mountain.domain;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity(name = "MOUNTAINS")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "Mountain.findByNameContaining", query = "select m from Mountain m where m.name = ?1")
public class Mountain {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "MOUNTAIN_NAME")
    @NotNull
    private String name;

    @NotNull
    private int height;

    @NotNull
    private String country;

    @Column(name = "MOUNTAIN_RANGE")
    @NotNull
    private String mountainRange;

}

