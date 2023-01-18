package com.crudApp.mountain.domain;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "MOUNTAINS")
@Getter
@NoArgsConstructor
//@NamedQuery(name = "Mountain.findByNameContaining", query = "select m from Mountain m where m.name = ?1")
public class Mountain {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long id;

    @Column(name = "MOUNTAIN_NAME")
    @NotNull
    private String name;

    @NotNull
    private int height;

    @NotNull
    private String country;

    @Transient
    private MountainRange mountainRange;

    public Mountain(long id, String name, int height, String country) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.country = country;
    }

    @ManyToOne
    @JoinColumn(name = "mountain_range_id")
    public MountainRange getMountainRange() {
        return mountainRange;
    }


}

