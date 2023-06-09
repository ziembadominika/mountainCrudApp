package com.crudApp.mountain.domain;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "USERS")
@NoArgsConstructor
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
    private List<Mountain> mountains;

    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    public static class UserEntityBuilder {
        private Long id;
        private String userName;
        private String firstName;
        private String lastName;
        private String email;
        private List<UserRating> userRatings = new ArrayList<>();
        private List<Mountain> mountains = new ArrayList<>();
        private String password;
        private List<Role> roles = new ArrayList<>();

        public UserEntityBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserEntityBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public UserEntityBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserEntityBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserEntityBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserEntityBuilder userRatings(UserRating userRating) {
            userRatings.add(userRating);
            return this;
        }

        public UserEntityBuilder mountains(Mountain mountain) {
            mountains.add(mountain);
            return this;
        }

        public UserEntityBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserEntityBuilder roles(Role role) {
            roles.add(role);
            return this;
        }

        public UserEntity build() {
            return new UserEntity(id, userName, firstName, lastName, email, userRatings, mountains, password, roles);
        }
    }

    private UserEntity (Long id, String userName, String firstName, String lastName, String email,
                        List<UserRating> userRatings, List<Mountain> mountains, String password, List<Role> roles) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userRatings = new ArrayList<>(userRatings);
        this.mountains = new ArrayList<>(mountains);
        this.password = password;
        this.roles = new ArrayList<>(roles);
    }

}

