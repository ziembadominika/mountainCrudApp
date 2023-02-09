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
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
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
    private UserRating userRatingTwo;
    private List<UserRating> userRatings = new ArrayList<>();
    private User userOne;
    private List<UserRating> userOneRatings;
    private List<Mountain> userOneMountains;
    private Mountain mountain;
    private Collection<GrantedAuthority> userOneRoles;

    @Before
    public void setUp() {
        userOne = new User(1L, "user97", "Susan", "Jones", 1997, 10, 12, "susan97@gmail.com", 2023, 01, 11, userOneRatings, userOneMountains, "password", userOneRoles);
        userRatingService = new UserRatingService(userRatingMapper, userRatingRepository);
        userRatingOne = new UserRating(1L, userOne, 5, mountain);
        userRatingTwo = new UserRating(2L, userOne, 4, mountain);
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
        Assert.assertEquals(userOne, userRatingDto.getUser());
    }

    @Test
    public void shouldAddUserRating() {
        //Given
        UserRatingDto userRatingDto = new UserRatingDto(1L, userOne, 5, mountain);
        //When
        userRatingService.addUserRating(userRatingDto);
        //Then
        verify(userRatingRepository, times(1)).save(any(UserRating.class));
    }

    @Test
    public void shouldUpdateUserRating() {
        //Given
        UserRatingDto userRatingDto = new UserRatingDto(1L, userOne, 4, mountain);
        //When
        userRatingService.updateUserRating(userRatingDto);
        //Then
        Assert.assertEquals(4, userRatingDto.getRate());
    }

    @Test
    public void shouldDeleteUserRating() {
        //Given
        UserRatingDto userRatingDto = new UserRatingDto(1L, userOne, 4, mountain);
        //When
        Long idToDelete = userRatingDto.getId();
        userRatingService.deleteUserRating(1L);
        //Then
        verify(userRatingRepository, times(1)).deleteById(idToDelete);
    }
}