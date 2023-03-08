package com.crudApp.mountain.domain;

import lombok.*;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "USERS")
@NoArgsConstructor
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "USER_NAME", nullable = false, unique = true)
    private String userName;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    private String email;
    @OneToMany
    @JoinColumn(name = "USER_ID")
    private List<UserRating> userRatings;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "MOUNTAINS_USERS",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "MOUNTAIN_ID"))
    private List<Mountain> mountains = new ArrayList<>();

    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
               joinColumns=@JoinColumn(name = "user_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id"))
    private List<Role>roles = new ArrayList<>();

    public UserEntity(long id, String userName, String firstName, String lastName, String email,
                      List<UserRating> userRatings, List<Mountain> userMountains,
                      String password, List<Role> roles) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userRatings = userRatings;
        this.mountains = userMountains;
        this.password = password;
        this.roles = roles;
    }
}

