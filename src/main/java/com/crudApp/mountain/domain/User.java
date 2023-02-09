package com.crudApp.mountain.domain;

import com.crudApp.mountain.deserializer.DateHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;


@Entity
@Table(name = "USERS")
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "DATE_OF_BIRTH")
    @JsonDeserialize(using = DateHandler.class)
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate birthDate;

    private String email;

    @Column(name = "DATE_OF_REGISTRATION")
    @JsonDeserialize(using = DateHandler.class)
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate dateOfRegistration;

    @OneToMany
    @JoinColumn(name = "USER_ID")
    @JsonIgnore
    private List<UserRating> userRatings;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE}, mappedBy = "users")
    @JsonIgnore
    private List<Mountain> mountains = new ArrayList<>();

    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
               joinColumns=@JoinColumn(name = "user_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id"))
    private List<Role>roles = new ArrayList<>();

    public User(long id, String userName, String firstName, String lastName, int yearOfBirth, int monthOfBirth,
                int dayOfBirth, String email, int yearOfRegistration, int monthOfRegistration, int dayOfRegistration,
                List<UserRating> userRatings, List<Mountain> userMountains, String password, Collection<GrantedAuthority> roles) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth);
        this.email = email;
        this.dateOfRegistration = LocalDate.of(yearOfRegistration, monthOfRegistration, dayOfRegistration);
        this.userRatings = userRatings;
        this.mountains = userMountains;
        this.password = password;
        this.roles = roles;
    }
}

