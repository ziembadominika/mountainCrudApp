package com.crudApp.mountain.mapper;

import com.crudApp.mountain.domain.UserRating;
import com.crudApp.mountain.domain.UserRatingDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(uses = {MountainMapper.class, UserMapper.class}, componentModel = "spring")
public interface UserRatingMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "userEntity", target = "userEntityDto")
    @Mapping(source = "rate", target = "rate")
    @Mapping(source = "mountain", target = "mountainDto")
    UserRatingDto mapToUserRatingDto(UserRating userRating);

    @InheritInverseConfiguration(name = "mapToUserRatingDto")
    UserRating mapToUserRating(UserRatingDto userRatingDto);

    @IterableMapping(elementTargetType = UserRatingDto.class)
    List<UserRatingDto>mapToUserRatingDtoList(List<UserRating>userRatings);

}
