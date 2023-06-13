package com.crudApp.mountain.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntityDto {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private List<UserRatingDto> userRatings;
    private List<MountainDto> mountains;
    private String password;
    private List<Role>roles;

    public static class UserEntityDtoBuilder {
        private Long id;
        private String userName;
        private String firstName;
        private String lastName;
        private String email;
        private List<UserRatingDto> userRatings;
        private List<MountainDto> mountains;
        private String password;
        private List<Role> roles;

        public UserEntityDto.UserEntityDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserEntityDto.UserEntityDtoBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public UserEntityDto.UserEntityDtoBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserEntityDto.UserEntityDtoBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserEntityDto.UserEntityDtoBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserEntityDto.UserEntityDtoBuilder userRatings(UserRatingDto userRatingDto) {
            userRatings.add(userRatingDto);
            return this;
        }

        public UserEntityDto.UserEntityDtoBuilder mountains(MountainDto mountainDto) {
            mountains.add(mountainDto);
            return this;
        }

        public UserEntityDto.UserEntityDtoBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserEntityDto.UserEntityDtoBuilder roles(Role role) {
            roles.add(role);
            return this;
        }

        public UserEntityDto build() {
            return new UserEntityDto(id, userName, firstName, lastName, email, userRatings, mountains, password, roles);
        }
    }
}
