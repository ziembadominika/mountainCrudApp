package com.crudApp.mountain.domain;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "MOUNTAINS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mountain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @Column(name = "MOUNTAIN_NAME")
    private String name;
    private int height;
    private String country;

    @ManyToOne
    @JoinColumn(name = "RANGE_ID")
    private MountainRange mountainRange;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "MOUNTAIN_ID")
    private List<UserRating> userRatings;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "userMountains")
    private List<User> users = new ArrayList<>();

    public double userRatingAverage() {
        return userRatings.stream()
                .map(u -> u.getRate())
                .reduce(0, (sum, current) -> sum += current) * 1.0 / userRatings.size();
    }

}

