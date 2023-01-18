package com.crudApp.mountain.service;

import com.crudApp.mountain.domain.Country;
import com.crudApp.mountain.domain.Mountain;
import com.crudApp.mountain.domain.MountainRange;
import com.crudApp.mountain.domain.MountainRangeDto;
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
import static org.mockito.Mockito.when;

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

    @Before
    public void setUp(){
        mountainRangeService = new MountainRangeService(mountainRangeMapper, mountainRangeRepository);
        List<Mountain> tatry = new ArrayList<>();
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

        Country poland = new Country(1L, "Poland", polishMountains);
    }

    @Test
    public void getAllMountainRanges() {
        //Given
        when(mountainRangeRepository.findAll()).thenReturn(polishMountains);

        //When
        List<MountainRangeDto> allRanges = mountainRangeService.getAllMountainRanges();
        //Then
        Assert.assertEquals(2, allRanges.size());
    }

    @Test
    public void getMountainRange() {
        //Given
        when(mountainRangeRepository.getReferenceById(1L)).thenReturn(tatraMountains);
        //When
        MountainRangeDto tatra = mountainRangeService.getMountainRange(1L);
        //Then
        Assert.assertEquals("Tatra Mountains", tatra.getRangeName());
    }

    @Test
    public void findMountainRangeByNameLike() {
        //Given

        //When

        //Then
    }

    @Test
    public void createMountainRange() {
        //Given

        //When

        //Then
    }

    @Test
    public void updateMountainRange() {
        //Given

        //When

        //Then
    }

    @Test
    public void deleteMountainRange() {
        //Given

        //When

        //Then
    }

    @Test
    public void getMountainsFromRange() {
        //Given

        //When

        //Then
    }
}