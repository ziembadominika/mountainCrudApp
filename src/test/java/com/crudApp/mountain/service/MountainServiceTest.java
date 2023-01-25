package com.crudApp.mountain.service;

import com.crudApp.mountain.domain.Mountain;
import com.crudApp.mountain.domain.MountainDto;
import com.crudApp.mountain.domain.MountainRange;
import com.crudApp.mountain.exception.MountainNotFoundException;
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


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class MountainServiceTest {

    private MountainService mountainService;

    @InjectMocks
    private MountainMapper mountainMapper = new MountainMapper();

    @Mock
    private MountainRepository mountainRepository;

    private Mountain mountainOne;
    private Mountain mountainTwo;
    private MountainRange sudety;
    private List<Mountain> mountainsList = new ArrayList<>();

    @Before
    public void setUp() {
        mountainService = new MountainService(mountainRepository, mountainMapper);
        mountainOne = new Mountain(1L, "Śnieżka", 1603, "Poland");
        mountainTwo = new Mountain(2L, "Śnieżnik", 1423, "Poland");
        mountainsList.add(mountainOne);
        mountainsList.add(mountainTwo);
        sudety = new MountainRange(1L, "Sudety", mountainsList);
    }

    @Test
    public void shouldGetAllMountains() {
        //Given
        when(mountainRepository.findAll()).thenReturn(mountainsList);

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
        assertEquals("Śnieżka", mountainDto.getName());
    }

    @Test
    public void shouldFindMountainByName() {
        //Given
        when(mountainRepository.findByNameLike("Śnie%")).thenReturn(mountainsList);
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
        Mountain mountainOne = new Mountain(1L, "Śnieżka", 1610, "Poland");
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
    public void shouldReturnMountainsWithHeightAbove() {
        //Given
        int height = 1400;
        when(mountainRepository.findAll()).thenReturn(mountainsList);
        //When
        List<MountainDto> mountainDtoList = mountainService.getMountainByHeight(height);
        //Then
        Assert.assertEquals(2, mountainDtoList.size());
    }

    @Test
    public void shouldReturnAverageRating(){

    }
}