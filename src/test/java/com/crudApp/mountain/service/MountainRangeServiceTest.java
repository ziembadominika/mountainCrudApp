package com.crudApp.mountain.service;

import com.crudApp.mountain.domain.*;
import com.crudApp.mountain.mapper.MountainMapper;
import com.crudApp.mountain.mapper.MountainRangeMapper;
import com.crudApp.mountain.repository.MountainRangeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
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
public class MountainRangeServiceTest {

    @InjectMocks
    private MountainRangeService mountainRangeService;
    @Mock
    private MountainRangeRepository mountainRangeRepository;
    @Mock
    private MountainRangeMapper mountainRangeMapper;
    @Mock
    private MountainMapper mountainMapper;

    private final List<MountainRange> polishMountains = new ArrayList<>();
    private final List<MountainRangeDto> polishMountainsDto = new ArrayList<>();
    private MountainRange tatraMountains;
    private MountainRangeDto tatraMountainsDto;
    private final List<Mountain> tatry = new ArrayList<>();
    private final List<MountainDto> tatryDto = new ArrayList<>();
    private final List<UserRating> userRatings = new ArrayList<>();
    private final List<UserRatingDto> userRatingsDto = new ArrayList<>();
    private final List<Mountain> sudetes = new ArrayList<>();
    private final List<MountainDto> sudetesDto = new ArrayList<>();
    public static Pageable firstPage = PageRequest.of(1, 5, Sort.by("name"));

    @Before
    public void setUp() {
        Mountain rysy = new Mountain(1L, "Rysy", 2499, tatraMountains, "Poland", "Europe", userRatings);
        Mountain łomnica = new Mountain(2L, "Łomnica", 2634, tatraMountains, "Slovakia", "Europe", userRatings);
        tatry.add(rysy);
        tatry.add(łomnica);
        MountainDto rysyDto = new MountainDto(1L, "Rysy", 2499, tatraMountainsDto, "Poland", "Europe", userRatingsDto);
        MountainDto łomnicaDto = new MountainDto(2L, "Łomnica", 2634, tatraMountainsDto, "Slovakia", "Europe", userRatingsDto);
        tatryDto.add(rysyDto);
        tatryDto.add(łomnicaDto);

        tatraMountains = new MountainRange(1L, "Tatra Mountains", tatry);
        tatraMountainsDto = new MountainRangeDto(1L, "Tatra Mountains", tatryDto);
        MountainRange theSudetes = new MountainRange(2L, "Sudetes", sudetes);
        MountainRangeDto theSudetesDto = new MountainRangeDto(2L, "Sudetes", sudetesDto);

        polishMountains.add(tatraMountains);
        polishMountains.add(theSudetes);
        polishMountainsDto.add(tatraMountainsDto);
        polishMountainsDto.add(theSudetesDto);
    }

    @Test
    public void shouldGetAllMountainRanges() {
        //Given
        when(mountainRangeRepository.findAll()).thenReturn(polishMountains);
        when(mountainRangeMapper.mapToMountainRangeDtoList(Optional.of(polishMountains))).thenReturn(Optional.of(polishMountainsDto));
        //When
        List<MountainRangeDto> allRanges = mountainRangeService.getAllMountainRanges();
        //Then
        Assert.assertEquals(2, allRanges.size());
    }
    @Test
    public void shouldGetMountainRange() {
        //Given
        when(mountainRangeRepository.getReferenceById(1L)).thenReturn(tatraMountains);
        when(mountainRangeMapper.mapToMountainRangeDto(tatraMountains)).thenReturn(tatraMountainsDto);
        //When
        MountainRangeDto tatra = mountainRangeService.getMountainRange(1L);
        //Then
        Assert.assertEquals("Tatra Mountains", tatra.getRangeName());
    }

    @Test
    public void shouldFindMountainRangeByNameLike() {
        //Given
        List<MountainRange> mountainRangeList = new ArrayList<>();
        mountainRangeList.add(tatraMountains);
        List<MountainRangeDto> mountainRangeDtos = new ArrayList<>();
        mountainRangeDtos.add(tatraMountainsDto);
        when(mountainRangeRepository.findByRangeNameContainingIgnoreCase("Tat", firstPage)).thenReturn(Optional.of(mountainRangeList));
        when(mountainRangeMapper.mapToMountainRangeDtoList(mountainRangeList)).thenReturn(mountainRangeDtos);
        //When
        Optional<List<MountainRangeDto>> mountainRangeDtoList = mountainRangeService.findMountainRangeByNameLike("Tat");
        //Then
        Assert.assertTrue(mountainRangeDtoList.isPresent());
    }

    @Test
    public void shouldCreateMountainRange() {
        //Given
        when(mountainRangeMapper.mapToMountainRange(tatraMountainsDto)).thenReturn(tatraMountains);
        //When
        mountainRangeService.createMountainRange(tatraMountainsDto);
        //Then
        verify(mountainRangeRepository, times(1)).save(any(MountainRange.class));
    }

    @Test
    public void shouldUpdateMountainRange() {
        //Given
        MountainRangeDto tatraMountainsDto = new MountainRangeDto(1L, "the Tatras", tatryDto);
        //When
        mountainRangeService.updateMountainRange(tatraMountainsDto);
        //Then
        Assert.assertEquals("the Tatras", tatraMountainsDto.getRangeName());
    }

    @Test
    public void shouldDeleteMountainRange() {
        //Given
        MountainRange mountainRange = new MountainRange(1L, "Tatra Mountains", tatry);
        Long mountainRangeId = mountainRange.getId();
        //When
        mountainRangeService.deleteMountainRange(mountainRangeId);
        //Then
        verify(mountainRangeRepository, times(1)).deleteById(mountainRangeId);
    }

    @Test
    public void shouldGetMountainsFromRange() {
        //Given
        Long rangeId = tatraMountains.getId();
        when(mountainRangeRepository.findById(rangeId)).thenReturn(Optional.ofNullable(tatraMountains));
        when(mountainMapper.mapToMountainDtoList(tatraMountains.getMountains())).thenReturn(tatraMountainsDto.getMountains());
        //When
        List<MountainDto> mountainsFromRange = mountainRangeService.getMountainsFromRange(rangeId);
        //Then
        Assert.assertEquals(2, mountainsFromRange.size());
    }
}