package com.crudApp.mountain.mapper;

import com.crudApp.mountain.domain.UserRating;
import com.crudApp.mountain.domain.UserRatingDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserRatingMapper {

    public UserRatingDto mapToUserRatingDto(UserRating userRating){
        return new UserRatingDto(userRating.getId(), userRating.getUserEntity(), userRating.getRate(),
                userRating.getMountain());
    }

    public UserRating mapToUserRating(UserRatingDto userRatingDto){
        return new UserRating(userRatingDto.getId(), userRatingDto.getUserEntity(), userRatingDto.getRate(),
                userRatingDto.getMountain());
    }

    public List<UserRatingDto>mapToUserRatingDtoList(List<UserRating>userRatings){
        return userRatings.stream()
                .map(u->mapToUserRatingDto(u))
                .collect(Collectors.toList());
    }
}
