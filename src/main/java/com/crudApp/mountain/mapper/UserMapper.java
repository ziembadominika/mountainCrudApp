package com.crudApp.mountain.mapper;

import com.crudApp.mountain.domain.UserEntity;
import com.crudApp.mountain.domain.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDto mapToUserDto(UserEntity userEntity){
        return new UserDto(
                userEntity.getId(),
                userEntity.getUserName(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getBirthDate().getYear(),
                userEntity.getBirthDate().getMonthValue(),
                userEntity.getBirthDate().getDayOfMonth(),
                userEntity.getEmail(),
                userEntity.getDateOfRegistration().getYear(),
                userEntity.getDateOfRegistration().getMonthValue(),
                userEntity.getDateOfRegistration().getDayOfMonth(),
                userEntity.getUserRatings(),
                userEntity.getMountains(),
                userEntity.getPassword(),
                userEntity.getRoles());
    }

    public UserEntity mapToUser(UserDto userDto){
        return new UserEntity(
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
                userDto.getMountains(),
                userDto.getPassword(),
                userDto.getRoles());
    }

    public List<UserDto>mapToUserDtoList(List<UserEntity> userEntityList){
        return userEntityList.stream()
                .map(u->new UserDto(u.getId(), u.getUserName(), u.getFirstName(), u.getLastName(), u.getBirthDate().getDayOfYear(), u.getBirthDate().getMonthValue(),
                        u.getBirthDate().getDayOfMonth(), u.getEmail(), u.getDateOfRegistration().getYear(), u.getDateOfRegistration().getMonthValue(),
                        u.getDateOfRegistration().getDayOfMonth(),u.getUserRatings(), u.getMountains(), u.getPassword(), u.getRoles()))
                .collect(Collectors.toList());
    }
}
