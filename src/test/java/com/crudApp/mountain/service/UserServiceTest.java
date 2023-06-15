package com.crudApp.mountain.service;

import com.crudApp.mountain.domain.*;
import com.crudApp.mountain.mapper.MountainMapper;
import com.crudApp.mountain.mapper.UserMapper;
import com.crudApp.mountain.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private MountainMapper mountainMapper;
    @InjectMocks
    private UserService userService;
    private UserEntity userEntityOne;
    private UserEntity userEntityTwo;
    private UserEntityDto userEntityOneDto;
    private final List<UserEntity> usersList = new ArrayList<>();
    private final List<UserEntityDto> usersListDto = new ArrayList<>();
    private MountainRange tatraMountains;
    private MountainRangeDto tatraMountainsDto;
    private List<UserRating> userRatings;
    private List<UserRatingDto> userRatingsDto;
    private UserRating userOneRatings;
    private UserRatingDto userOneRatingsDto;
    private UserRating userTwoRatings;
    private UserRatingDto userTwoRatingsDto;
    private Mountain userOneMountains;
    private MountainDto userOneMountainsDto;
    private Mountain userTwoMountains;
    private MountainDto userTwoMountainsDto;
    public static Pageable firstPage = PageRequest.of(1, 5, Sort.by("name"));

    @Before
    public void setUp() {
        userService = new UserService(userRepository, userMapper, mountainMapper);
        userEntityOne = new UserEntity.UserEntityBuilder()
                .id(1L)
                .userName("user97")
                .firstName("Susan")
                .lastName("Jones")
                .email("susan97@gmail.com")
                .userRatings(userOneRatings)
                .mountains(userOneMountains)
                .build();
        userEntityTwo = new UserEntity.UserEntityBuilder()
                .id(2L)
                .userName("mountain_addict")
                .firstName("Thomas")
                .lastName("Evans")
                .email("evanst@gmail.com")
                .userRatings(userTwoRatings)
                .mountains(userTwoMountains)
                .build();

        usersList.add(userEntityOne);
        usersList.add(userEntityTwo);
        userEntityOneDto = new UserEntityDto.UserEntityDtoBuilder()
                .id(1L)
                .userName("user97")
                .firstName("Susan")
                .lastName("Jones")
                .email("susan97@gmail.com")
                .userRatings(userOneRatingsDto)
                .mountains(userOneMountainsDto)
                .build();
        UserEntityDto userEntityTwoDto = new UserEntityDto.UserEntityDtoBuilder()
                .id(2L)
                .userName("mountain_addict")
                .firstName("Thomas")
                .lastName("Evans")
                .email("evanst@gmail.com")
                .userRatings(userTwoRatingsDto)
                .mountains(userTwoMountainsDto)
                .build();
        usersListDto.add(userEntityOneDto);
        usersListDto.add(userEntityTwoDto);

    }

    @Test
    public void shouldGetAllUsers() {
        //Given
        when(userRepository.findAll()).thenReturn(usersList);
        when(userMapper.mapToUserDtoList(usersList)).thenReturn(usersListDto);
        //When
        List<UserEntityDto> resultList = userService.getAllUsers();
        //Then
        Assert.assertEquals(2, resultList.size());
    }

    @Test
    public void shouldGetUser() {
        //Given
        when(userRepository.getReferenceById(1L)).thenReturn(userEntityOne);
        when(userMapper.mapToUserDto(userEntityOne)).thenReturn(userEntityOneDto);
        //When
        UserEntityDto userEntityDto = userService.getUser(1L);
        //Then
        Assert.assertEquals("user97", userEntityDto.getUserName());
    }

    @Test
    public void shouldFindUseByUserNameContaining() {
        //Given
        List<UserEntity> usersList = new ArrayList<>();
        usersList.add(userEntityOne);
        when(userRepository.findByUserName("Su")).thenReturn(userEntityOne);
        when(userMapper.mapToUserDto(userEntityOne)).thenReturn(userEntityOneDto);
        //When
        userService.findUserByUserNameContaining("Su");
        //Then
        Assert.assertEquals(1, usersList.size());
    }

    @Test
    public void shouldCreateUser() {
        //Given
        when(userMapper.mapToUser(userEntityOneDto)).thenReturn(userEntityOne);
        //When
        userService.createUser(userEntityOneDto);
        //Then
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    public void shouldUpdateUser() {
        //Given

        userEntityOne = new UserEntity.UserEntityBuilder()
                .id(1L)
                .userName("Susan97")
                .firstName("Susan")
                .lastName("Jones")
                .email("susan97@gmail.com")
                .userRatings(userOneRatings)
                .mountains(userOneMountains)
                .build();

        userEntityOneDto = new UserEntityDto.UserEntityDtoBuilder()
                .id(1L)
                .userName("Susan97")
                .firstName("Susan")
                .lastName("Jones")
                .email("susan97@gmail.com")
                .userRatings(userOneRatingsDto)
                .mountains(userOneMountainsDto)
                .build();
        when(userMapper.mapToUserDto(userEntityOne)).thenReturn(userEntityOneDto);
        when(userMapper.mapToUser(userEntityOneDto)).thenReturn(userEntityOne);
        //When
        UserEntityDto updatedUser = userService.updateUser(userEntityOneDto);
        //Then
        Assert.assertEquals("Susan97", updatedUser.getUserName());
    }

    @Test
    public void shouldDeleteUser() {
        //Given
        Long userId = userEntityOne.getId();
        //When
        userService.deleteUser(userId);
        //Then
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    public void shouldGetUsersMountains() {
        //Given
        Mountain rysy = new Mountain(1L, "Rysy", 2499, tatraMountains, "Poland", "Europe", userRatings);
        Mountain łomnica = new Mountain(2L, "Łomnica", 2634, tatraMountains, "Slovakia", "Europe", userRatings);
        userEntityOne = new UserEntity.UserEntityBuilder()
                .id(1L)
                .userName("Susan97")
                .firstName("Susan")
                .lastName("Jones")
                .email("susan97@gmail.com")
                .userRatings(userOneRatings)
                .mountains(userOneMountains)
                .build();
        userOneMountains.add(rysy);
        userOneMountains.add(łomnica);
        MountainDto rysyDto = new MountainDto(1L, "Rysy", 2499, tatraMountainsDto, "Poland", "Europe", userRatingsDto);
        MountainDto łomnicaDto = new MountainDto(2L, "Łomnica", 2634, tatraMountainsDto, "Slovakia", "Europe", userRatingsDto);
        userOneMountainsDto.add(rysyDto);
        userOneMountainsDto.add(łomnicaDto);
        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntityOne));
        when(mountainMapper.mapToMountainDtoList(userOneMountains)).thenReturn(userOneMountainsDto);
        //When
        List<MountainDto> mountainList = userService.getUserMountains(userEntityOne.getId());
        //Then
        Assert.assertEquals(2, mountainList.size());
    }
}