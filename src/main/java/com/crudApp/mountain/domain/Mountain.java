package com.crudApp.mountain.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "MOUNTAINS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mountain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "MOUNTAIN_NAME")
    private String name;

    private int height;

    @ManyToOne
    @JoinColumn(name = "RANGE_ID")
    private MountainRange mountainRange;

    private String country;

    private String continent;

    @OneToMany(
            mappedBy = "mountain",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<UserRating> userRatings;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE})
    @JoinTable(
            name = "MOUNTAINS_USERS",
            joinColumns = @JoinColumn(name = "MOUNTAIN_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    private Set<User> users = new HashSet<>();

    public double userRatingAverage() {
        return userRatings.stream()
                .map(u -> u.getRate())
                .reduce(0, (sum, current) -> sum += current) * 1.0 / userRatings.size();
    }
}

