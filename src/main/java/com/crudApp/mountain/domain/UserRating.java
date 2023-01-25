package com.crudApp.mountain.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column
    private int rate;

    @ManyToOne
    @JoinColumn(name = "MOUNTAIN_ID")
    private Long mountainId;

    public int getRate() {
        return rate;
    }
}
