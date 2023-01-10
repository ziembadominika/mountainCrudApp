package com.crudApp.mountain.service;

import com.crudApp.mountain.domain.Mountain;
import com.crudApp.mountain.domain.MountainDto;
import com.crudApp.mountain.mapper.MountainMapper;
import com.crudApp.mountain.repository.MountainRepository;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MountainServiceTest {

    private Mountain mountainOne;
    private MountainService mountainService;

    List<Mountain> mountainsList = new ArrayList<>();

    @InjectMocks
    private MountainMapper mountainMapper;

    @Mock
    private MountainRepository mountainRepository;

    @Before
    public void setUp() {
        mountainService = new MountainService(mountainRepository, mountainMapper);
        Mountain mountainOne = new Mountain(1L, "Śnieżka", 1603, "Poland", "Sudety");
//        Mountain mountainTwo = new Mountain(2L, "Śnieżnik", 1423, "Poland", "Sudety");
//        Mountain mountainThree = new Mountain(3L, "Rysy", 456, "Poland", "Sudety");
        mountainsList.add(mountainOne);
//        mountainsList.add(mountainTwo);
//        mountainsList.add(mountainThree);
    }

    @Test
    public void getAllMountainsTest() {
        //Given
        when(mountainRepository.findAll()).thenReturn(mountainsList);

        //When
        List<MountainDto> mountainDtosList = mountainService.getAllMountains();

        //Then
        assertEquals(1, mountainDtosList.size());
    }

    @Test
    public void getMountainTest(){
        //Given
        Mountain mountainOne = new Mountain(1L, "Śnieżka", 1603, "Poland", "Sudety");
        when(mountainRepository.getReferenceById(1L)).thenReturn(mountainOne);

        //When
        MountainDto mountainDto = mountainService.getMountain(1L);

        //Then
        assertEquals("Śnieżka", mountainOne.getName());
    }

//    @Test
//    public void findMountainByNameTest(){
        //Given
//        when(mountainRepository.findByNameContaining("Śnie")).thenReturn(mountainsList);
//        //When
//        List<MountainDto>mountainDtos = mountainService.findAllMountainByNameContaining("Śnie");
//        //Then
//        Assert.assertEquals(2, mountainDtos.size());
//
//        Mountain mountain = new Mountain(3L, "Rysy", 456, "Poland", "Sudety");
//        MountainDto mountainDto = mountainMapper.mapToMountainDto(mountain);
//        mountainService.saveMountain(mountainDto);
//        List<MountainDto>resultList = new ArrayList<>();
//        mountainService.findAllMountainByNameContaining(mountain.getName()).forEach(m->resultList.add(m));
//        assertEquals(resultList.size(), 1);
//    }

    @Test
    public void saveMountainTest(){
        //Given
        Mountain mountainOne = new Mountain(1L, "Śnieżka", 1603, "Poland", "Sudety");
        MountainDto mountainTest = mountainMapper.mapToMountainDto(mountainOne);

        //When
        mountainService.saveMountain(mountainTest);

        //Then
        verify(mountainRepository, times(1)).save(mountainOne);
    }

    @Test
    public void updateMountainTest(){
        //Given
        MountainDto mountainDto = new MountainDto(1L, "Śnieżka", 1610, "Poland", "Sudety");

        //When
        MountainDto testResult = mountainService.updateMountain(mountainDto);

        //Then
        Assert.assertEquals(testResult.getHeight(), 1610);
    }

    @Test
    public void deleteMountainTest(){
        //Given
        Mountain mountainOne = new Mountain(1L, "Śnieżka", 1603, "Poland", "Sudety");
        Long mountainId = mountainOne.getId();

        //When
        mountainService.deleteMountain(mountainId);

        //Then
        verify(mountainRepository, times(1)).deleteById(mountainId);

    }
}