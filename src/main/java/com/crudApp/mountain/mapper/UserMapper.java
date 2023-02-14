package com.crudApp.mountain.mapper;

import com.crudApp.mountain.domain.UserEntity;
import com.crudApp.mountain.domain.UserEntityDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserEntityDto mapToUserDto(UserEntity userEntity){
        return new UserEntityDto(
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

    public UserEntity mapToUser(UserEntityDto userEntityDto){
        return new UserEntity(
                userEntityDto.getId(),
                userEntityDto.getUserName(),
                userEntityDto.getFirstName(),
                userEntityDto.getLastName(),
                userEntityDto.getDateOfRegistration().getYear(),
                userEntityDto.getDateOfRegistration().getMonthValue(),
                userEntityDto.getDateOfRegistration().getDayOfMonth(),
                userEntityDto.getEmail(),
                userEntityDto.getDateOfRegistration().getYear(),
                userEntityDto.getDateOfRegistration().getMonthValue(),
                userEntityDto.getDateOfRegistration().getDayOfMonth(),
                userEntityDto.getUserRatings(),
                userEntityDto.getMountains(),
                userEntityDto.getPassword(),
                userEntityDto.getRoles());
    }

    public List<UserEntityDto>mapToUserDtoList(List<UserEntity> userEntityList){
        return userEntityList.stream()
                .map(u->new UserEntityDto(u.getId(), u.getUserName(), u.getFirstName(), u.getLastName(), u.getBirthDate().getDayOfYear(), u.getBirthDate().getMonthValue(),
                        u.getBirthDate().getDayOfMonth(), u.getEmail(), u.getDateOfRegistration().getYear(), u.getDateOfRegistration().getMonthValue(),
                        u.getDateOfRegistration().getDayOfMonth(),u.getUserRatings(), u.getMountains(), u.getPassword(), u.getRoles()))
                .collect(Collectors.toList());
    }
}
