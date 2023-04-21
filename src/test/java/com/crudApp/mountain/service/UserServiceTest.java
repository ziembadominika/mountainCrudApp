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
    private UserEntityDto userEntityOneDto;
    private final List<UserEntity> usersList = new ArrayList<>();
    private final List<UserEntityDto> usersListDto = new ArrayList<>();
    private MountainRange tatraMountains;
    private MountainRangeDto tatraMountainsDto;
    private List<UserRating> userRatings;
    private List<UserRatingDto> userRatingsDto;
    private List<UserRating> userOneRatings;
    private List<UserRatingDto> userOneRatingsDto;
    private List<UserRating> userTwoRatings;
    private List<UserRatingDto> userTwoRatingsDto;
    private final List<Mountain> userOneMountains = new ArrayList<>();
    private final List<MountainDto> userOneMountainsDto = new ArrayList<>();
    private List<Mountain> userTwoMountains;
    private List<MountainDto> userTwoMountainsDto;
    public static Pageable firstPage = PageRequest.of(1, 5, Sort.by("name"));

    @Before
    public void setUp() {
        userService = new UserService(userRepository, userMapper, mountainMapper);
        userEntityOne = new UserEntity(1L, "user97", "Susan", "Jones", "susan97@gmail.com", userOneRatings,
                userOneMountains);
        UserEntity userEntityTwo = new UserEntity(2L, "mountain_addict", "Thomas", "Evans", "evanst@gmail.com", userTwoRatings,
                userTwoMountains);

        usersList.add(userEntityOne);
        usersList.add(userEntityTwo);
        userEntityOneDto = new UserEntityDto(1L, "user97", "Susan", "Jones", "susan97@gmail.com", userOneRatingsDto,
                userOneMountainsDto);
        UserEntityDto userEntityTwoDto = new UserEntityDto(2L, "mountain_addict", "Thomas", "Evans", "evanst@gmail.com",
                userTwoRatingsDto, userTwoMountainsDto);
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
        UserEntity userEntityOne = new UserEntity(1L, "Susan97", "Susan", "Jones", "susan97@gmail.com", userOneRatings,
                userOneMountains);
        userEntityOneDto = new UserEntityDto(1L, "Susan97", "Susan", "Jones", "susan97@gmail.com", userOneRatingsDto,
                userOneMountainsDto);
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
        userEntityOne = new UserEntity(1L, "user97", "Susan", "Jones", "susan97@gmail.com", userOneRatings,
                userOneMountains);
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