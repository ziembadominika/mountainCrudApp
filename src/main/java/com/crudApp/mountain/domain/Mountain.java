package com.crudApp.mountain.domain;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.List;


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

    @ManyToOne
    @JoinColumn(name = "RANGE_ID")
    private Long rangeId;

    @Column(name = "MOUNTAIN_NAME")
    @NotNull
    private String name;

    @NotNull
    private int height;

    @NotNull
    private String country;


    public Mountain(long id, String name, int height, String country) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.country = country;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "MOUNTAIN_ID")
    private List<UserRating> mountainRatings;

    public double userRatingAverage() {
        return mountainRatings.stream()
                .map(u -> u.getRate())
                .reduce(0, (sum, current) -> sum += current) * 1.0 / mountainRatings.size();
    }
}

