package com.crudApp.mountain.service;

import com.crudApp.mountain.domain.UserRating;
import com.crudApp.mountain.domain.UserRatingDto;
import com.crudApp.mountain.mapper.UserRatingMapper;
import com.crudApp.mountain.repository.UserRatingRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class UserRatingService {

    private final UserRatingMapper userRatingMapper;
    private final UserRatingRepository userRatingRepository;

    public List<UserRatingDto> getAllUserRatings() {
        return userRatingMapper.mapToUserRatingDtoList(userRatingRepository.findAll());
    }

    public UserRatingDto getUserRating(Long ratingId) {
        return userRatingMapper.mapToUserRatingDto(userRatingRepository.getReferenceById(ratingId));
    }

    public void addUserRating(UserRatingDto userRatingDto) {
        UserRating userRating = userRatingMapper.mapToUserRating(userRatingDto);
        userRatingRepository.save(userRating);
    }

    public UserRatingDto updateUserRating(UserRatingDto userRatingDto) {
        UserRating userRating = userRatingMapper.mapToUserRating(userRatingDto);
        userRatingRepository.save(userRating);
        return userRatingMapper.mapToUserRatingDto(userRating);
    }

    public void deleteUserRating(Long ratingId) {
        userRatingRepository.deleteById(ratingId);
    }

}
