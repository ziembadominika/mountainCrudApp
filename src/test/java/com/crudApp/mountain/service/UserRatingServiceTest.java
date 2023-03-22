package com.crudApp.mountain.service;

import com.crudApp.mountain.domain.*;
import com.crudApp.mountain.mapper.UserRatingMapper;
import com.crudApp.mountain.repository.UserRatingRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserRatingServiceTest {

    @InjectMocks
    private UserRatingMapper userRatingMapper;
    @Mock
    private UserRatingRepository userRatingRepository;
    private UserRatingService userRatingService;
    private UserRating userRatingOne;
    private final List<UserRating> userRatings = new ArrayList<>();
    private UserEntity userEntityOne;
    private List<UserRating> userOneRatings;
    private List<Mountain> userOneMountains;
    private Mountain mountain;
    private List<Role> userOneRoles;

    @Before
    public void setUp() {
        userEntityOne = new UserEntity(1L, "user97", "Susan", "Jones", "susan97@gmail.com", userOneRatings, userOneMountains);
        userRatingService = new UserRatingService(userRatingMapper, userRatingRepository);
        userRatingOne = new UserRating(1L, userEntityOne, 5, mountain);
        UserRating userRatingTwo = new UserRating(2L, userEntityOne, 4, mountain);
        userRatings.add(userRatingOne);
        userRatings.add(userRatingTwo);
    }

    @Test
    public void shouldGetAllUserRatings() {
        //Given
        when(userRatingRepository.findAll()).thenReturn(userRatings);
        //When
        List<UserRatingDto> userRatingList = userRatingService.getAllUserRatings();
        //Then
        Assert.assertEquals(2, userRatingList.size());
    }

    @Test
    public void shouldGetUserRating() {
        //Given
        when(userRatingRepository.getReferenceById(1L)).thenReturn(userRatingOne);
        //When
        UserRatingDto userRatingDto = userRatingService.getUserRating(1L);
        //Then
        Assert.assertEquals(userEntityOne, userRatingDto.getUserEntity());
    }

    @Test
    public void shouldAddUserRating() {
        //Given
        UserRatingDto userRatingDto = new UserRatingDto(1L, userEntityOne, 5, mountain);
        //When
        userRatingService.addUserRating(userRatingDto);
        //Then
        verify(userRatingRepository, times(1)).save(any(UserRating.class));
    }

    @Test
    public void shouldUpdateUserRating() {
        //Given
        UserRatingDto userRatingDto = new UserRatingDto(1L, userEntityOne, 4, mountain);
        //When
        userRatingService.updateUserRating(userRatingDto);
        //Then
        Assert.assertEquals(4, userRatingDto.getRate());
    }

    @Test
    public void shouldDeleteUserRating() {
        //Given
        UserRatingDto userRatingDto = new UserRatingDto(1L, userEntityOne, 4, mountain);
        //When
        Long idToDelete = userRatingDto.getId();
        userRatingService.deleteUserRating(1L);
        //Then
        verify(userRatingRepository, times(1)).deleteById(idToDelete);
    }
}