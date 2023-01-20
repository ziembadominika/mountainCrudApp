package com.crudApp.mountain.service;

import com.crudApp.mountain.domain.*;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class MountainRangeServiceTest {

    private MountainRangeService mountainRangeService;

    @Mock
    private MountainRangeRepository mountainRangeRepository;

    @InjectMocks
    private MountainRangeMapper mountainRangeMapper;

    List<MountainRange> polishMountains = new ArrayList<>();
    MountainRange tatraMountains;
    List<Mountain> tatry = new ArrayList<>();

    @Before
    public void setUp(){
//        mountainRangeService = new MountainRangeService(mountainRangeRepository, mountainRangeMapper);

        Mountain rysy = new Mountain(1L, "Rysy", 2499, "Poland");
        Mountain łomnica = new Mountain(2L, "Łomnica", 2634, "Slovakia");
        tatry.add(rysy);
        tatry.add(łomnica);

        List<Mountain> sudety = new ArrayList<>();
        Mountain śnieżnik = new Mountain(3L, "Śnieżnik", 1423, "Poland");
        Mountain śnieżka = new Mountain(4L, "Śnieżka", 1603, "Poland");
        sudety.add(śnieżnik);
        sudety.add(śnieżka);

        tatraMountains = new MountainRange(1L, "Tatra Mountains", tatry);
        MountainRange sudetyMountains = new MountainRange(2L, "Sudety", sudety);

        polishMountains.add(tatraMountains);
        polishMountains.add(sudetyMountains);

        Country poland = new Country(1L, "Poland");
    }

    @Test
    public void shouldGetAllMountainRanges() {
        //Given
        when(mountainRangeRepository.findAll()).thenReturn(polishMountains);

        //When
        List<MountainRangeDto> allRanges = mountainRangeService.getAllMountainRanges();
        //Then
        Assert.assertEquals(2, allRanges.size());
    }

    @Test
    public void shouldGetMountainRange() {
        //Given
        when(mountainRangeRepository.getReferenceById(1L)).thenReturn(tatraMountains);
        //When
        MountainRangeDto tatra = mountainRangeService.getMountainRange(1L);
        //Then
        Assert.assertEquals("Tatra Mountains", tatra.getRangeName());
    }

    @Test
    public void shouldFindMountainRangeByNameLike() {
        //Given

        //When

        //Then
    }

    @Test
    public void shouldCreateMountainRange() {
        //Given
        MountainRangeDto mountainRangeDto = mountainRangeMapper.mapToMountainRangeDto(tatraMountains);
        //When
        mountainRangeService.createMountainRange(mountainRangeDto);
        //Then
        verify(mountainRangeRepository, times(1)).save(any(MountainRange.class));
    }

    @Test
    public void shouldUpdateMountainRange() {
        //Given
        MountainRangeDto mountainRangeDto = new MountainRangeDto(3L, "Tatra Mountains", tatry);
        //When
        mountainRangeService.updateMountainRange(mountainRangeDto);
        //Then
        Assert.assertEquals(3L, mountainRangeDto.getId());
    }

    @Test
    public void shouldDeleteMountainRange() {
        //Given
        MountainRangeDto mountainRangeDto = new MountainRangeDto(3L, "Tatra Mountains", tatry);
        Long mountainRangeId = mountainRangeDto.getId();
        //When
        mountainRangeService.deleteMountainRange(mountainRangeId);
        //Then
        verify(mountainRangeRepository, times(1)).deleteById(mountainRangeId);
    }

//    @Test
//    public void shouldGetMountainsFromRange() {
//        //Given
//        MountainRangeDto mountainRangeDto = new MountainRangeDto(3L, "Tatra Mountains", tatry);
//        String rangeName = mountainRangeDto.getRangeName();
//
//        //When
//        List<MountainDto> mountainsFromRange = mountainRangeService.getMountainsFromRange(rangeName);
//        //Then
//        Assert.assertEquals(2, mountainsFromRange.size());
//    }
}