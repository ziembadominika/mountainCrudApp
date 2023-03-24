package com.crudApp.mountain.service;

import com.crudApp.mountain.domain.*;
import com.crudApp.mountain.mapper.MountainMapper;
import com.crudApp.mountain.repository.MountainRepository;

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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class MountainServiceTest {

    @InjectMocks
    private MountainService mountainService;
    @Mock
    private MountainMapper mountainMapper;
    @Mock
    private MountainRepository mountainRepository;

    private MountainDto mountainOne;
    private MountainDto mountainTwo;
    private MountainDto mountainThree;
    private MountainRange sudetes;
    private final List<MountainDto> mountainDtoList = new ArrayList<>();
    private final List<UserRating> userRatings = new ArrayList<>();
    private UserEntity userEntity;

    @Before
    public void setUp() {
        mountainOne = new MountainDto(1L, "Śnieżka", 1603, sudetes, "Poland", "Europe", userRatings);
        mountainTwo = new MountainDto(2L, "Śnieżnik", 1423, sudetes, "Poland", "Europe", userRatings);
        mountainThree = new MountainDto(3L, "Lomnica", 2600, sudetes, "Slovakia", "Europe", userRatings);
        mountainDtoList.add(mountainOne);
        mountainDtoList.add(mountainTwo);
        sudetes = new MountainRange(1L, "Sudetes", mountainDtoList);
    }

    @Test
    public void shouldGetAllMountains() {
        //Given
        when(mountainRepository.findAll()).thenReturn(mountainDtoList);
        when(mountainMapper.mapToMountainDtoList(anyList())).thenReturn()
        //When
        List<MountainDto> mountainDtoList = mountainService.getAllMountains();
        //Then
        assertEquals(2, mountainDtoList.size());
    }

    @Test
    public void shouldGetMountain() {
        //Given
        when(mountainRepository.getReferenceById(1L)).thenReturn(mountainOne);
        //When
        MountainDto mountainDto = mountainService.getMountain(1L);
        //Then
        assertEquals("Śnieżka", mountainDto.getMountainName());
    }

    @Test
    public void shouldFindMountainByName() {
        //Given
        when(mountainRepository.findByName("Śnie%", MountainService.firstPage)).thenReturn(mountainsList);
        //When
        List<MountainDto> mountainDtos = mountainService.findMountainByNameLike("Śnie");
        mountainDtos.size();
        //Then
        Assert.assertEquals(2, mountainDtos.size());
    }

    @Test
    public void shouldSaveMountain() {
        //Given
        MountainDto mountainOneDto = mountainMapper.mapToMountainDto(mountainOne);
        //When
        mountainService.createMountain(mountainOneDto);
        //Then
        verify(mountainRepository, times(1)).save(any(Mountain.class));
    }

    @Test
    public void shouldUpdateMountain() {
        //Given
        Mountain mountainOne = new Mountain(1L, "Śnieżka", 1610, sudetes, "Poland", "Europe", userRatings);
        MountainDto mountainDto = mountainMapper.mapToMountainDto(mountainOne);
        //When
        MountainDto updatedMountain = mountainService.updateMountain(mountainDto);
        //Then
        Assert.assertEquals(updatedMountain.getHeight(), 1610);
    }

    @Test
    public void shouldDeleteMountain() {
        //Given
        Long mountainId = mountainOne.getId();
        //When
        mountainService.deleteMountain(mountainId);
        //Then
        verify(mountainRepository, times(1)).deleteById(mountainId);
    }

    @Test
    public void shouldReturnAllMountainsAboveGivenHeight() {
        //Given
        int height = 1400;
        when(mountainRepository.findAll()).thenReturn(mountainsList);
        //When
        List<MountainDto> mountainDtoList = mountainService.getMountainByHeight(height);
        //Then
        Assert.assertEquals(2, mountainDtoList.size());
    }

    @Test
    public void shouldGetUserRatingForMountain() {
        //Given
        mountainOne = new Mountain(1L, "Śnieżka", 1603, sudetes, "Poland", "Europe", userRatings);
        UserRating userRating = new UserRating(1L, userEntity, 5, mountainOne);
        userRatings.add(userRating);
        when(mountainRepository.findById(1L)).thenReturn(Optional.of(mountainOne));
        //When
        double averageRating = mountainService.getUserRatingForMountain(mountainOne.getId());
        //Then
        Assert.assertEquals(5, averageRating, 0.1);
    }

    @Test
    public void shouldGetMountainsByCountry() {
        //Given
        List<Mountain> mountains = new ArrayList<>();
        mountains.add(mountainOne);
        mountains.add(mountainTwo);
        mountains.add(mountainThree);
        when(mountainRepository.findAll()).thenReturn(mountains);
        //When
        List<MountainDto> mountainDtoList = mountainService.getMountainsByCountry("Poland");
        //Then
        Assert.assertEquals(2, mountainDtoList.size());
    }

    @Test
    public void shouldGetMountainsByContinent() {
        //Given
        List<Mountain> mountains = new ArrayList<>();
        mountains.add(mountainOne);
        mountains.add(mountainTwo);
        when(mountainRepository.findAll()).thenReturn(mountains);
        //When
        List<MountainDto> mountainDtoList = mountainService.getMountainsByContinent("Europe");
        //Then
        Assert.assertEquals(2, mountainDtoList.size());
    }
}