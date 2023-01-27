package com.crudApp.mountain.service;

import com.crudApp.mountain.domain.*;
import com.crudApp.mountain.mapper.ContinentMapper;
import com.crudApp.mountain.repository.ContinentRepository;
import org.junit.Assert;
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
public class ContinentServiceTest {

    @Mock
    private ContinentRepository continentRepository;

    @InjectMocks
    private ContinentMapper continentMapper;

    private ContinentService continentService;

    private Continent europe;

    private Continent asia;

    private Continent continent;
    private MountainRange tatraMountains;
    private List<UserRating> userRatings = new ArrayList<>();
    private List<Continent>continents = new ArrayList<>();
    private List<Mountain> tatry = new ArrayList<>();
    private List<User> usersList;

    public void SetUp(){
        continentService = new ContinentService(continentMapper, continentRepository);
        europe = new Continent(1L, "Europe");
        asia = new Continent(2L, "Asia");
        continents.add(europe);
        continents.add(asia);
        Mountain rysy = new Mountain(1L, "Rysy", 2499, "Poland", tatraMountains, userRatings, usersList);
        Mountain łomnica = new Mountain(2L, "Łomnica", 2634, "Slovakia", tatraMountains, userRatings, usersList);
        tatry.add(rysy);
        tatry.add(łomnica);
    }

    @Test
    public void getAllContinents() {
        //Given
        continentService = new ContinentService(continentMapper, continentRepository);
        europe = new Continent(1L, "Europe");
        asia = new Continent(2L, "Asia");
        List<Continent>continents = new ArrayList<>();
        continents.add(europe);
        continents.add(asia);
        when(continentRepository.findAll()).thenReturn(continents);
        //When
        List<ContinentDto> allContinents = continentService.getAllContinents();
        //Then
        Assert.assertEquals(2, allContinents.size());
    }

    @Test
    public void getContinent() {
        //Given
        europe = new Continent(1L, "Europe");
        continentService = new ContinentService(continentMapper, continentRepository);
        when(continentRepository.getReferenceById(1L)).thenReturn(europe);
        //When
        ContinentDto continentDto = continentService.getContinent(1L);
        //Then
        Assert.assertEquals(1L, continentDto.getId());
    }

    @Test
    public void saveContinent() {
        //Given
        continentService = new ContinentService(continentMapper, continentRepository);
        europe = new Continent(1L, "Europe");
        ContinentDto continentDto = continentMapper.mapToContinentDto(europe);
        //When
        continentService.saveContinent(continentDto);
        //Then
        verify(continentRepository, times(1)).save(any(Continent.class));
    }

    @Test
    public void updateContinent() {
        //Given
        continentService = new ContinentService(continentMapper, continentRepository);
        europe = new Continent(3L, "Europe");
        ContinentDto updatedContinent = continentMapper.mapToContinentDto(europe);
        //When
        continentService.updateContinent(updatedContinent);
        //Then
        Assert.assertEquals(3L, updatedContinent.getId());
    }

    @Test
    public void deleteContinent() {
        //Given
        europe = new Continent(1L, "Europe");
        continentService = new ContinentService(continentMapper, continentRepository);
        Long continentId = europe.getId();
        //When
        continentService.deleteContinent(continentId);
        //Then
        verify(continentRepository, times(1)).deleteById(continentId);
    }

    @Test
    public void shouldGetMountainsFromContinent(){
//        //Given
//        when(continent.getMountainFromContinent("Europe")).thenReturn(tatry);
//        //When
//        List<MountainDto> mountainDtos = continentService.getMountainsFromContinent("Europe");
//        //Then
//        Assert.assertEquals(2, mountainDtos.size());

    }
}