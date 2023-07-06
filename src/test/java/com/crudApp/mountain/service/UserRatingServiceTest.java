package com.crudApp.mountain.service;

import com.crudApp.mountain.domain.*;
import com.crudApp.mountain.mapper.MountainMapper;
import com.crudApp.mountain.mapper.UserMapper;
import com.crudApp.mountain.mapper.UserRatingMapper;
import com.crudApp.mountain.repository.UserRatingRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserRatingServiceTest {

    @Mock
    private UserRatingMapper userRatingMapper;
    @Mock
    private UserRatingRepository userRatingRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private MountainMapper mountainMapper;
    @InjectMocks
    private UserRatingService userRatingService;
    private UserRating userRatingOne;
    private UserRatingDto userRatingOneDto;
    private final List<UserRating> userRatings = new ArrayList<>();
    private final List<UserRatingDto> userRatingDtos = new ArrayList<>();
    private UserEntity userEntityOne;
    private List<UserRating> userOneRatings;
    private List<Mountain> userOneMountains;
    private Mountain mountain;
    private UserEntityDto userEntityOneDto;
    private List<MountainDto> userOneMountainsDto;
    private MountainDto mountainDto;
    private List<UserRatingDto> userOneRatingsDto;
    private UserRating userOneRatingOne;
    private UserRating userOneRatingTwo;
    private Mountain denali;
    private UserRatingDto userOneRatingOneDto;
    private UserRatingDto userOneRatingTwoDto;
    private MountainDto denaliDto;

    @Before
    public void setUp() {
        userEntityOne = new UserEntity.UserEntityBuilder()
                .id(1L)
                .userName("user97")
                .firstName("Susan")
                .lastName("Jones")
                .email("susan97@gmail.com")
                .userRatings(userOneRatingOne)
                .userRatings(userOneRatingTwo)
                .mountains(denali)
                .build();
        userRatingOne = new UserRating(1L, userEntityOne, 5, mountain);
        userRatingOneDto = new UserRatingDto(1L, userEntityOneDto, 5, mountainDto);
        UserRating userRatingTwo = new UserRating(2L, userEntityOne, 4, mountain);
        userRatings.add(userRatingOne);
        userRatings.add(userRatingTwo);
        UserRatingDto userOneRatingDto = new UserRatingDto(1L, userEntityOneDto, 5, mountainDto);
        UserRatingDto userRatingDtoTwo = new UserRatingDto(2L, userEntityOneDto, 4, mountainDto);
        userRatingDtos.add(userOneRatingDto);
        userRatingDtos.add(userRatingDtoTwo);
        userEntityOneDto = new UserEntityDto.UserEntityDtoBuilder()
                .id(1L)
                .userName("user97")
                .firstName("Susan")
                .lastName("Jones")
                .email("susan97@gmail.com")
                .userRatings(userOneRatingOneDto)
                .userRatings(userOneRatingTwoDto)
                .mountains(denaliDto)
                .build();
    }

    @Test
    public void shouldGetAllUserRatings() {
        //Given
        when(userRatingRepository.findAll()).thenReturn(userRatings);
        when(userRatingMapper.mapToUserRatingDtoList(userRatings)).thenReturn(userRatingDtos);
        //When
        List<UserRatingDto> userRatingList = userRatingService.getAllUserRatings();
        //Then
        assertEquals(2, userRatingList.size());
    }

    @Test
    public void shouldGetUserRating() {
        //Given
        when(userRatingRepository.getReferenceById(1L)).thenReturn(userRatingOne);
        when(userRatingMapper.mapToUserRatingDto(userRatingOne)).thenReturn(userRatingOneDto);
        //When
        UserRatingDto userRatingDto = userRatingService.getUserRating(1L);
        //Then
        assertEquals(5, userRatingDto.getRate());
    }

    @Test
    public void shouldAddUserRating() {
        //Given
        UserRatingDto userRatingDto = new UserRatingDto(1L, userMapper.mapToUserDto(userEntityOne), 5,
                mountainMapper.mapToMountainDto(mountain));
        when(userRatingMapper.mapToUserRating(userRatingDto)).thenReturn(userRatingOne);
        //When
        userRatingService.addUserRating(userRatingDto);
        //Then
        verify(userRatingRepository, times(1)).save(any(UserRating.class));
    }

    @Test
    public void shouldUpdateUserRating() {
        //Given
        UserRatingDto userRatingDto = new UserRatingDto(1L, userMapper.mapToUserDto(userEntityOne), 4,
                mountainMapper.mapToMountainDto(mountain));
        when(userRatingMapper.mapToUserRating(userRatingDto)).thenReturn(userRatingOne);
        //When
        userRatingService.updateUserRating(userRatingDto);
        //Then
        assertEquals(4, userRatingDto.getRate());
    }

    @Test
    public void shouldDeleteUserRating() {
        //Given
        UserRatingDto userRatingDto = new UserRatingDto(1L,userMapper.mapToUserDto(userEntityOne), 4, mountainMapper.mapToMountainDto(mountain));
        //When
        Long idToDelete = userRatingDto.getId();
        userRatingService.deleteUserRating(1L);
        //Then
        verify(userRatingRepository, times(1)).deleteById(idToDelete);
    }
}