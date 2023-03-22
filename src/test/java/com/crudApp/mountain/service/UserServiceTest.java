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
    @InjectMocks
    private UserMapper userMapper = new UserMapper();
    @InjectMocks
    private MountainMapper mountainMapper;
    private UserService userService;
    private UserEntity userEntityOne;
    private final List<UserEntity> usersList = new ArrayList<>();
    private List<UserRating> userOneRatings;
    private final List<Mountain> userOneMountains = new ArrayList<>();
    private List<UserRating> userTwoRatings;
    private List<Mountain> userTwoMountains;
    private MountainRange tatraMountains;
    private List<UserRating> userRatings;
    private List<Role> userOneRoles;
    private List<Role> userTwoRoles;

    @Before
    public void setUp() {
        userService = new UserService(userRepository, userMapper, mountainMapper);
        userEntityOne = new UserEntity(1L, "user97", "Susan", "Jones", "susan97@gmail.com", userOneRatings,
                userOneMountains);
        UserEntity userEntityTwo = new UserEntity(2L, "mountain_addict", "Thomas", "Evans", "evanst@gmail.com", userTwoRatings,
                userTwoMountains);
        usersList.add(userEntityOne);
        usersList.add(userEntityTwo);
    }

    @Test
    public void shouldGetAllUsers() {
        //Given
        when(userRepository.findAll()).thenReturn(usersList);
        //When
        List<UserEntityDto> resultList = userService.getAllUsers();
        //Then
        Assert.assertEquals(2, resultList.size());
    }

    @Test
    public void shouldGetUser() {
        //Given
        when(userRepository.getReferenceById(1L)).thenReturn(userEntityOne);
        //When
        UserEntityDto userEntityDto = userService.getUser(1L);
        //Then
        Assert.assertEquals("user97", userEntityDto.getUserName());
    }

    @Test
    public void shouldFindUseByUserNameContaining() {
        //Given
        List<UserEntity> usersList = new ArrayList<>();
        userEntityOne = new UserEntity(1L, "user97", "Susan", "Jones", "susan97@gmail.com", userOneRatings,
                userOneMountains);
        usersList.add(userEntityOne);
        when(userRepository.findByUserName("Su")).thenReturn(userEntityOne);
        //When
        userService.findUserByUserNameContaining("Su");
        //Then
        Assert.assertEquals(1, usersList.size());
    }

    @Test
    public void shouldCreateUser() {
        //Given
        UserEntityDto userEntityDto = userMapper.mapToUserDto(userEntityOne);
        //When
        userService.createUser(userEntityDto);
        //Then
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    public void shouldUpdateUser() {
        //Given
        UserEntity userEntityOne = new UserEntity(1L, "Susan97", "Susan", "Jones", "susan97@gmail.com", userOneRatings,
                userOneMountains);
        UserEntityDto userEntityDto = userMapper.mapToUserDto(userEntityOne);

        //When
        UserEntityDto updatedUser = userService.updateUser(userEntityDto);

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
        List<Mountain> userOneMountains = new ArrayList<>();
        userEntityOne = new UserEntity(1L, "user97", "Susan", "Jones", "susan97@gmail.com", userOneRatings,
                userOneMountains);
        Mountain rysy = new Mountain(1L, "Rysy", 2499, tatraMountains, "Poland", "Europe", userRatings);
        Mountain łomnica = new Mountain(2L, "Łomnica", 2634, tatraMountains, "Slovakia", "Europe", userRatings);
        userOneMountains.add(rysy);
        userOneMountains.add(łomnica);
        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntityOne));
        //When
        List<MountainDto> mountainList = userService.getUserMountains(userEntityOne.getId());
        //Then
        Assert.assertEquals(2, mountainList.size());
    }
}