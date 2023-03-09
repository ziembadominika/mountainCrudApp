package com.crudApp.mountain.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MOUNTAINS")
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
    @JsonIgnore
    private List<UserRating> usersRatings;

    public double userRatingAverage() {
        return usersRatings.stream()
                .map(UserRating::getRate)
                .reduce(0, Integer::sum) * 1.0 / usersRatings.size();
    }
}

