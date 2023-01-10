package com.crudApp.mountain.mapper;

import com.crudApp.mountain.domain.User;
import com.crudApp.mountain.domain.UserDto;

import java.util.List;

public class UserMapper {

    public UserDto mapToUserDto(User user){
        return new UserDto(
                user.getId(),
                user.getUserName(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthDate().getYear(),
                user.getBirthDate().getMonthValue(),
                user.getBirthDate().getDayOfMonth(),
                user.getEmail(),
                user.getDateOfRegistration().getYear(),
                user.getDateOfRegistration().getMonthValue(),
                user.getDateOfRegistration().getDayOfMonth());
    }

    public User mapToUser(UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getUserName(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getBirthDate().getYear(),
                userDto.getBirthDate().getMonthValue(),
                userDto.getBirthDate().getDayOfMonth(),
                userDto.getEmail(),
                userDto.getDateOfRegistration().getYear(),
                userDto.getDateOfRegistration().getMonthValue(),
                userDto.getDateOfRegistration().getDayOfMonth());
    }
}
