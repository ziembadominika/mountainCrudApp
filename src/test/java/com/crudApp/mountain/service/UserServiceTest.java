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
    private User userOne;
    private User userTwo;
    private List<User> usersList = new ArrayList<>();
    private List<UserRating> userOneRatings;
    private List<Mountain> userOneMountains = new ArrayList<>();
    private List<UserRating> userTwoRatings;
    private List<Mountain> userTwoMountains;
    private MountainRange tatraMountains;
    private List<UserRating> userRatings;

    @Before
    public void setUp() {
        userService = new UserService(userRepository, userMapper, mountainMapper);
        userOne = new User(1L, "user97", "Susan", "Jones", 1997, 10, 12, "susan97@gmail.com", 2023, 1, 11, userOneRatings, userOneMountains);
        userTwo = new User(2L, "mountain_addict", "Thomas", "Evans", 1980, 5, 27, "evanst@gmail.com", 2023, 1, 11, userTwoRatings, userTwoMountains);
        usersList.add(userOne);
        usersList.add(userTwo);
    }

    @Test
    public void shouldGetAllUsers() {
        //Given
        when(userRepository.findAll()).thenReturn(usersList);
        //When
        List<UserDto> resultList = userService.getAllUsers();
        //Then
        Assert.assertEquals(2, resultList.size());
    }

    @Test
    public void shouldGetUser() {
        //Given
        when(userRepository.getReferenceById(1L)).thenReturn(userOne);
        //When
        UserDto userDto = userService.getUser(1L);
        //Then
        Assert.assertEquals("user97", userDto.getUserName());
    }

    @Test
    public void shouldFindUseByUserNameContaining() {
        //Given
        List<User> usersList = new ArrayList<>();
        userOne = new User(1L, "user97", "Susan", "Jones", 1997, 10, 12, "susan97@gmail.com", 2023, 1, 11, userOneRatings, userOneMountains);
        usersList.add(userOne);
        when(userRepository.findByUserNameContaining("Su")).thenReturn(usersList);
        //When
        userService.findUserByUserNameContaining("Su");
        //Then
        Assert.assertEquals(1, usersList.size());
    }

    @Test
    public void shouldCreateUser() {
        //Given
        UserDto userDto = userMapper.mapToUserDto(userOne);
        //When
        userService.createUser(userDto);
        //Then
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void shouldUpdateUser() {
        //Given
        User userOne = new User(1L, "Susan97", "Susan", "Jones", 1998, 10, 12, "susan97@gmail.com", 2023, 1, 11, userOneRatings, userOneMountains);
        UserDto userDto = userMapper.mapToUserDto(userOne);

        //When
        UserDto updatedUser = userService.updateUser(userDto);

        //Then
        Assert.assertEquals("Susan97", updatedUser.getUserName());
    }

    @Test
    public void shouldDeleteUser() {
        //Given
        Long userId = userOne.getId();
        //When
        userService.deleteUser(userId);
        //Then
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    public void shouldGetUsersMountains() {
        //Given
        List<Mountain> userOneMountains = new ArrayList<>();
        userOne = new User(1L, "user97", "Susan", "Jones", 1997, 10, 12, "susan97@gmail.com", 2023, 1, 11, userOneRatings, userOneMountains);
        Mountain rysy = new Mountain(1L, "Rysy", 2499, tatraMountains, "Poland", "Europe", userRatings, usersList);
        Mountain łomnica = new Mountain(2L, "Łomnica", 2634, tatraMountains, "Slovakia", "Europe", userRatings, usersList);
        userOneMountains.add(rysy);
        userOneMountains.add(łomnica);
        when(userRepository.getReferenceById(userOne.getId())).thenReturn(userOne);
        //When
        List<MountainDto> mountainList = userService.getUserMountains(userOne.getId());
        //Then
        Assert.assertEquals(2, mountainList.size());
    }
}