package com.crudApp.mountain.service;

import com.crudApp.mountain.domain.Mountain;
import com.crudApp.mountain.domain.User;
import com.crudApp.mountain.domain.UserRating;
import com.crudApp.mountain.domain.UserRatingDto;
import com.crudApp.mountain.mapper.UserRatingMapper;
import com.crudApp.mountain.repository.UserRatingRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserRatingServiceTest {

    private UserRatingService userRatingService;

    @InjectMocks
    private UserRatingMapper userRatingMapper;

    @Mock
    private UserRatingRepository userRatingRepository;

    private UserRating userRatingOne;
    private UserRating userRatingTwo;
    private List<UserRating> userRatings;
    private User userOne;
    private List<UserRating> userOneRatings;
    private List<Mountain> userOneMountains;
    private Mountain mountain;


    public void setUp(){
        userOne = new User(1L, "user97", "Susan", "Jones", 1997, 10, 12, "susan97@gmail.com", 2023, 01, 11, userOneRatings, userOneMountains);
        userRatingService = new UserRatingService(userRatingMapper, userRatingRepository);
        userRatingOne = new UserRating(1L, userOne, 5, mountain);
        userRatingTwo = new UserRating(2L, userOne, 4, mountain);
        userRatings.add(userRatingOne);
        userRatings.add(userRatingTwo);
    }
//    @Test
//    public void getAllUserRatings() {
//        //Given
//        userOne = new User(1L, "user97", "Susan", "Jones", 1997, 10, 12, "susan97@gmail.com", 2023, 01, 11, userOneRatings, userOneMountains);
//        userRatingService = new UserRatingService(userRatingMapper, userRatingRepository);
//        userRatingOne = new UserRating(1L, userOne, 5, mountain);
//        userRatingTwo = new UserRating(2L, userOne, 4, mountain);
//        userRatings.add(userRatingOne);
//        userRatings.add(userRatingTwo);
//        when(userRatingRepository.findAll()).thenReturn(userRatings);
//        //When
//        List<UserRatingDto> userRatingList = userRatingService.getAllUserRatings();
//        //Then
//        Assert.assertEquals(2, userRatingList.size());
//    }
//
//    @Test
//    public void getUserRating() {
//        //Given
//        userRatingOne = new UserRating(1L, userOne, 5, mountain);
//        when(userRatingRepository.getReferenceById(1L)).thenReturn(userRatingOne);
//        //When
//        UserRatingDto userRatingDto = userRatingService.getUserRating(1L);
//        //Then
//        Assert.assertEquals(userOne, userRatingDto.getUser());
//    }
//
//    @Test
//    public void addUserRating() {
//        //Given
//        UserRatingDto userRatingDto= new UserRatingDto(1L, userOne, 5, mountain);
//        //When
//        userRatingService.addUserRating(userRatingDto);
//        //Then
//        verify(userRatingRepository, times(1)).save(any(UserRating.class));
//    }
//
//    @Test
//    public void updateUserRating() {
//        //Given
//        UserRatingDto userRatingDto= new UserRatingDto(1L, userOne, 4, mountain);
//        //When
//        userRatingService.updateUserRating(userRatingDto);
//        //Then
//        Assert.assertEquals(4, userRatingDto.getRate());
//    }
//
//    @Test
//    public void deleteUserRating() {
//        //Given
//        UserRatingDto userRatingDto= new UserRatingDto(1L, userOne, 4, mountain);
//        //When
//        Long idToDelete = userRatingDto.getId();
//        userRatingService.deleteUserRating(1L);
//        //Then
//        verify(userRatingRepository, times(1)).deleteById(idToDelete);
//    }
}