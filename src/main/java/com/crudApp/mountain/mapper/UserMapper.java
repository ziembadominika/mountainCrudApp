package com.crudApp.mountain.mapper;

import com.crudApp.mountain.domain.UserEntity;
import com.crudApp.mountain.domain.UserEntityDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "userName", target = "userName")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(ignore = true, target = "userRatings")
    @Mapping(ignore = true, target = "mountains")
    UserEntityDto mapToUserDto(UserEntity userEntity);

    @InheritInverseConfiguration(name = "mapToUserDto")
    UserEntity mapToUser(UserEntityDto userEntityDto);

    @IterableMapping(elementTargetType = UserEntityDto.class)
    List<UserEntityDto> mapToUserDtoList(List<UserEntity> userEntityList);
}
