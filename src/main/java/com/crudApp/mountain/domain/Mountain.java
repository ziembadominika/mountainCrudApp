package com.crudApp.mountain.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MOUNTAINS")
@Getter
@Setter
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
    @JsonIgnore
    private MountainRange mountainRange;

    private String country;

    private String continent;

    @OneToMany(
            mappedBy = "mountain",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UserRating> userRatings;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE})
    @JoinTable(
            name = "MOUNTAINS_USERS",
            joinColumns = @JoinColumn(name = "MOUNTAIN_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    @JsonIgnore
    private List<UserEntity> userEntities = new ArrayList<>();

    public double userRatingAverage() {
        return userRatings.stream()
                .map(u -> u.getRate())
                .reduce(0, (sum, current) -> sum += current) * 1.0 / userRatings.size();
    }
}

