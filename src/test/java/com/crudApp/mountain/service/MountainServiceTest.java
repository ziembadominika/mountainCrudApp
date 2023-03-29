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

    private Mountain mountainOne;
    private MountainRange sudetes;
    private final List<Mountain> mountainList = new ArrayList<>();
    private final List<UserRating> userRatings = new ArrayList<>();
    private UserEntity userEntity;
    private MountainDto mountainOneDto;
    private MountainRangeDto sudetesDto;
    private List<UserRatingDto> userRatingsDto;
    private final List<MountainDto> mountainDtoList = new ArrayList<>();

    @Before
    public void setUp() {
        mountainOne = new Mountain(1L, "Śnieżka", 1603, sudetes, "Poland", "Europe", userRatings);
        Mountain mountainTwo = new Mountain(2L, "Śnieżnik", 1423, sudetes, "Poland", "Europe", userRatings);
        Mountain mountainThree = new Mountain(3L, "Ślęża", 718, sudetes, "Poland", "Europe", userRatings);
        mountainList.add(mountainOne);
        mountainList.add(mountainTwo);
        mountainList.add(mountainThree);
        sudetes = new MountainRange(1L, "Sudetes", mountainList);
        mountainOneDto = new MountainDto(1L, "Śnieżka", 1603, sudetesDto, "Poland", "Europe", userRatingsDto);
        MountainDto mountainTwoDto = new MountainDto(2L, "Śnieżnik", 1423, sudetesDto, "Poland", "Europe", userRatingsDto);
        MountainDto mountainThreeDto = new MountainDto(3L, "Ślęża", 718, sudetesDto, "Poland", "Europe", userRatingsDto);
        mountainDtoList.add(mountainOneDto);
        mountainDtoList.add(mountainTwoDto);
        mountainDtoList.add(mountainThreeDto);
    }

    @Test
    public void shouldGetAllMountains() {
        //Given
        when(mountainRepository.findAll()).thenReturn(mountainList);
        when(mountainMapper.mapToMountainDtoList(mountainList)).thenReturn(mountainDtoList);
        //When
        List<MountainDto> mountainDtoList = mountainService.getAllMountains();
        //Then
        assertEquals(3, mountainDtoList.size());
    }

    @Test
    public void shouldGetMountain() {
        //Given
        when(mountainRepository.getReferenceById(1L)).thenReturn(mountainOne);
        when(mountainMapper.mapToMountainDto(mountainOne)).thenReturn(mountainOneDto);
        //When
        MountainDto mountainDto = mountainService.getMountain(1L);
        //Then
        assertEquals("Śnieżka", mountainDto.getMountainName());
    }

    @Test
    public void shouldFindMountainByName() {
        //Given
        when(mountainRepository.searchByName("Ś", MountainService.firstPage)).thenReturn(mountainList);
        when(mountainMapper.mapToMountainDtoList(mountainList)).thenReturn(mountainDtoList);
        //When
        List<MountainDto> mountainDtos = mountainService.findMountainByNameLike("Ś");
        //Then
        Assert.assertEquals(3, mountainDtos.size());
    }

    @Test
    public void shouldSaveMountain() {
        //Given
        when(mountainMapper.mapToMountain(mountainOneDto)).thenReturn(mountainOne);
        //When
        mountainService.createMountain(mountainOneDto);
        //Then
        verify(mountainRepository, times(1)).save(any(Mountain.class));
    }

    @Test
    public void shouldUpdateMountain() {
        //Given
        mountainOne = new Mountain(1L, "Śnieżka", 1610, sudetes, "Poland", "Europe", userRatings);
        mountainOneDto = new MountainDto(1L, "Śnieżka", 1610, sudetesDto, "Poland", "Europe", userRatingsDto);
        when(mountainMapper.mapToMountain(mountainOneDto)).thenReturn(mountainOne);
        when(mountainMapper.mapToMountainDto(mountainOne)).thenReturn(mountainOneDto);
        //When
        MountainDto updatedMountain = mountainService.updateMountain(mountainOneDto);
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
        when(mountainRepository.findAll()).thenReturn(mountainList);
        when(mountainMapper.mapToMountainDtoList(mountainList)).thenReturn(mountainDtoList);
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
        when(mountainRepository.findAll()).thenReturn(mountainList);
        when(mountainMapper.mapToMountainDtoList(mountainList)).thenReturn(mountainDtoList);
        //When
        List<MountainDto> mountainDtoList = mountainService.getMountainsByCountry("Poland");
        //Then
        Assert.assertEquals(3, mountainDtoList.size());
    }

    @Test
    public void shouldGetMountainsByContinent() {
        //Given
        when(mountainRepository.findAll()).thenReturn(mountainList);
        when(mountainMapper.mapToMountainDtoList(mountainList)).thenReturn(mountainDtoList);
        //When
        List<MountainDto> mountainDtoList = mountainService.getMountainsByContinent("Europe");
        //Then
        Assert.assertEquals(3, mountainDtoList.size());
    }
}