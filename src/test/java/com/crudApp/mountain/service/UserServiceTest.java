package com.crudApp.mountain.service;

import com.crudApp.mountain.domain.User;
import com.crudApp.mountain.domain.UserDto;
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

    private UserService userService;

    private User userOne;

    private User userTwo;

    private List<User> usersList = new ArrayList<>();


    @Before
    public void setUp(){
        userService = new UserService(userRepository, userMapper);
        userOne = new User(1L, "user97", "Susan", "Jones", 1997, 10, 12, "susan97@gmail.com", 2023, 01, 11);
        userTwo = new User(2L, "mountain_addict", "Thomas", "Evans", 1980, 05, 27, "evanst@gmail.com", 2023, 01, 11);
        usersList.add(userOne);
        usersList.add(userTwo);
    }

    @Test
    public void getAllUsers() {
        //Given
        when(userRepository.findAll()).thenReturn(usersList);
        //When
        List<UserDto> resultList = userService.getAllUsers();
        //Then
        Assert.assertEquals(2, resultList.size());
    }

    @Test
    public void getUser() {
        //Given
        when(userRepository.getReferenceById(1L)).thenReturn(userOne);
        //When
        UserDto userDto = userService.getUser(1L);
        //Then
        Assert.assertEquals("user97", userDto.getUserName());
    }

    @Test
    public void findUseByNameContaining() {
        //Given

        //When

        //Then
    }

    @Test
    public void createUser() {
        //Given
        UserDto userDto = userMapper.mapToUserDto(userOne);
        //When
        userService.createUser(userDto);
        //Then
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void updateUser() {
        //Given
        User userOne = new User(1L, "Susan97", "Susan", "Jones", 1998, 10, 12, "susan97@gmail.com", 2023, 01, 11);
        UserDto userDto = userMapper.mapToUserDto(userOne);

        //When
        UserDto updatedUser = userService.updateUser(userDto);

        //Then
        Assert.assertEquals("Susan97", updatedUser.getUserName());
    }

    @Test
    public void deleteUser() {
        //Given
        Long userId = userOne.getId();
        //When
        userService.deleteUser(userId);
        //Then
        verify(userRepository, times(1)).deleteById(userId);
    }
}