package com.crudApp.mountain.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
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

    @OneToMany(
            targetEntity = Mountain.class,
            mappedBy = "mountainRange",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    public List<Mountain> getMountainsFromRange(String rangeName) {
        return mountains;
    }
}
