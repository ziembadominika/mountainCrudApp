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
    private MountainRange alaska;
    private MountainRangeDto alaskaDto;
    private List<UserRating> userRatings;
    private List<UserRatingDto> userRatingsDto;
    private UserRating userOneRatingOne;
    private UserRating userOneRatingTwo;
    private UserRatingDto userOneRatingOneDto;
    private UserRatingDto userOneRatingTwoDto;
    private UserRating userTwoRatingOne;
    private UserRatingDto userTwoRatingOneDto;
    private Mountain denali;
    private MountainDto denaliDto;
    private Mountain mountEverest;
    private MountainDto mountEverestDto;

    @Before
    public void setUp() {
        userService = new UserService(userRepository, userMapper, mountainMapper);
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
        userEntityTwo = new UserEntity.UserEntityBuilder()
                .id(2L)
                .userName("mountain_addict")
                .firstName("Thomas")
                .lastName("Evans")
                .email("evanst@gmail.com")
                .userRatings(userTwoRatingOne)
                .mountains(mountEverest)
                .build();

        usersList.add(userEntityOne);
        usersList.add(userEntityTwo);
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
        UserEntityDto userEntityTwoDto = new UserEntityDto.UserEntityDtoBuilder()
                .id(2L)
                .userName("mountain_addict")
                .firstName("Thomas")
                .lastName("Evans")
                .email("evanst@gmail.com")
                .userRatings(userTwoRatingOneDto)
                .mountains(mountEverestDto)
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
                .userRatings(userOneRatingOne)
                .mountains(denali)
                .build();

        userEntityOneDto = new UserEntityDto.UserEntityDtoBuilder()
                .id(1L)
                .userName("Susan97")
                .firstName("Susan")
                .lastName("Jones")
                .email("susan97@gmail.com")
                .userRatings(userOneRatingOneDto)
                .mountains(denaliDto)
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
        denali = new Mountain(1L, "Denali", 6190, alaska, "USA", "North America", userRatings);
        Mountain mountGilbert = new Mountain(2L, "Mount Gilbert", 818, alaska, "USA", "North America", userRatings);
        userEntityOne = new UserEntity.UserEntityBuilder()
                .id(1L)
                .userName("Susan97")
                .firstName("Susan")
                .lastName("Jones")
                .email("susan97@gmail.com")
                .userRatings(userOneRatingOne)
                .mountains(denali)
                .mountains(mountGilbert)
                .build();
        denaliDto = new MountainDto(1L, "Denali", 6190, alaskaDto, "USA", "North America", userRatingsDto);
        MountainDto mountGilbertDto = new MountainDto(2L, "Mount Gilbert", 818, alaskaDto, "USA", "North America", userRatingsDto);
        List<MountainDto> mountainsDto = new ArrayList<>();
        mountainsDto.add(denaliDto);
        mountainsDto.add(mountGilbertDto);
        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntityOne));
        when(mountainMapper.mapToMountainDtoList(userEntityOne.getMountains())).thenReturn(mountainsDto);
        //When
        List<MountainDto> mountainList = userService.getUserMountains(userEntityOne.getId());
        //Then
        Assert.assertEquals(2, mountainList.size());
    }
}