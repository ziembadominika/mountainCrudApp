package com.crudApp.mountain.mapper;

import com.crudApp.mountain.domain.User;
import com.crudApp.mountain.domain.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
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
                user.getDateOfRegistration().getDayOfMonth(),
                user.getUserRatings(),
                user.getMountains());
    }

    public User mapToUser(UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getUserName(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getDateOfRegistration().getYear(),
                userDto.getDateOfRegistration().getMonthValue(),
                userDto.getDateOfRegistration().getDayOfMonth(),
                userDto.getEmail(),
                userDto.getDateOfRegistration().getYear(),
                userDto.getDateOfRegistration().getMonthValue(),
                userDto.getDateOfRegistration().getDayOfMonth(),
                userDto.getUserRatings(),
                userDto.getMountains());
    }

    public List<UserDto>mapToUserDtoList(List<User>userList){
        return userList.stream()
                .map(u->new UserDto(u.getId(), u.getUserName(), u.getFirstName(), u.getLastName(), u.getBirthDate().getDayOfYear(), u.getBirthDate().getMonthValue(),
                        u.getBirthDate().getDayOfMonth(), u.getEmail(), u.getDateOfRegistration().getYear(), u.getDateOfRegistration().getMonthValue(),
                        u.getDateOfRegistration().getDayOfMonth(),u.getUserRatings(), u.getMountains()))
                .collect(Collectors.toList());
    }
}
