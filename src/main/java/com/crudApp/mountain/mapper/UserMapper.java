package com.crudApp.mountain.mapper;

import com.crudApp.mountain.domain.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(uses = {MountainMapper.class}, componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "userName", target = "userName")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(ignore = true, target = "userRatings")
    @Mapping(source = "mountains", target = "mountains")
    @Mapping(source = "roles", target = "roles")
    UserEntityDto mapToUserDto(UserEntity userEntity);

    @InheritInverseConfiguration
    @Mapping(ignore = true, target = "userRatings")
    UserEntity mapToUser(UserEntityDto userEntityDto);

    @IterableMapping(elementTargetType = UserEntityDto.class)
    List<UserEntityDto> mapToUserDtoList(List<UserEntity> userEntityList);
}
