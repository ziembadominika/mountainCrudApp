package com.crudApp.mountain.service;

import com.crudApp.mountain.domain.*;
import com.crudApp.mountain.mapper.CountryMapper;
import com.crudApp.mountain.repository.CountryRepository;
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
public class CountryServiceTest {

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CountryMapper countryMapper;

    private CountryService countryService;

    private List<Country> countries = new ArrayList<>();
    private Country poland;
    private Country slovakia;
    private List<MountainRange> polishMountains = new ArrayList<>();
    private MountainRange tatraMountains;
    private List<Mountain> tatry = new ArrayList<>();
    private List<UserRating> userRatings = new ArrayList<>();
    private Continent Europe;
    private List<MountainRange>slovakianMountains;
    private List<User> usersList;

    public void setUp() {
        countryService = new CountryService(countryMapper, countryRepository);

        poland = new Country(1L, "Poland", polishMountains, Europe);
        slovakia = new Country(2L, "Slovakia", slovakianMountains, Europe);
        countries.add(poland);
        countries.add(slovakia);

        Mountain rysy = new Mountain(1L, "Rysy", 2499, "Poland", tatraMountains, userRatings, usersList);
        Mountain łomnica = new Mountain(2L, "Łomnica", 2634, "Slovakia", tatraMountains, userRatings, usersList);
        tatry.add(rysy);
        tatry.add(łomnica);

        tatraMountains = new MountainRange(1L, "Tatra Mountains", tatry, countries);

        polishMountains.add(tatraMountains);
    }

    @Test
    public void getAllCountries() {
        //Given
        countryService = new CountryService(countryMapper, countryRepository);
        when(countryRepository.findAll()).thenReturn(countries);
        poland = new Country(1L, "Poland", polishMountains, Europe);
        slovakia = new Country(2L, "Slovakia", slovakianMountains, Europe);
        countries.add(poland);
        countries.add(slovakia);
        //When
        List<CountryDto> allCountries = countryService.getAllCountries();
        //Then
        Assert.assertEquals(2, allCountries.size());
        //CleanUp
        countryRepository.deleteById(2L);
    }

    @Test
    public void getCountry() {
        //Given
        countryService = new CountryService(countryMapper, countryRepository);
        poland = new Country(1L, "Poland", polishMountains, Europe);
        when(countryRepository.getReferenceById(1L)).thenReturn(poland);
        //When
        CountryDto countryDto = countryService.getCountry(1L);
        //Then
        Assert.assertEquals(1L, countryDto.getId());
    }

    @Test
    public void saveCountry() {
        //Given
        countryService = new CountryService(countryMapper, countryRepository);
        poland = new Country(1L, "Poland", polishMountains, Europe);
        CountryDto countryDto = countryMapper.mapToCountryDto(poland);
        //When
        countryService.saveCountry(countryDto);
        //Then
        verify(countryRepository, times(1)).save(any(Country.class));
    }

    @Test
    public void updateCountry() {
        //Given
        countryService = new CountryService(countryMapper, countryRepository);
        CountryDto poland = new CountryDto(3L, "Poland", polishMountains, Europe);
        //When
        CountryDto updatedCountry = countryService.updateCountry(poland);
        //Then
        Assert.assertEquals(3L, updatedCountry.getId());
    }

    @Test
    public void deleteCountry() {
        //Given
        countryService = new CountryService(countryMapper, countryRepository);
        poland = new Country(3L, "Poland", polishMountains, Europe);
        Long countryId = poland.getId();
        //When
        countryService.deleteCountry(countryId);
        //Then
        verify(countryRepository, times(1)).deleteById(countryId);
    }

    @Test
    public void shouldFindCountryByNameLike(){
        //Given
        countryService = new CountryService(countryMapper, countryRepository);
        poland = new Country(3L, "Poland", polishMountains, Europe);
        countries.add(poland);
        when(countryRepository.findByCountryNameLike("Pol")).thenReturn(countries);
        //When
        countryService.findByCountryNameLike("Pol");
        //Then
        Assert.assertEquals(1, countries.size());
    }


    @Test
    public void shouldGetMountainsFromCountry(){
//        //Given
//        countryService = new CountryService(countryMapper, countryRepository);
//        poland = new Country(3L, "Poland", polishMountains, Europe);
//        Mountain rysy = new Mountain(1L, "Rysy", 2499, "Poland", tatraMountains, userRatings);
//        Mountain łomnica = new Mountain(2L, "Łomnica", 2634, "Slovakia", tatraMountains, userRatings);
//        tatry.add(rysy);
//        tatry.add(łomnica);
//        tatraMountains = new MountainRange(1L, "Tatra Mountains", tatry, countries);
//        polishMountains.add(tatraMountains);
//        countries.add(poland);
//        when(countryRepository.findAll()).thenReturn(countries);
//        //When
//        List<MountainDto> testList = countryService.getMountainsFromCountry("Poland");
//
//        //Then
//        Assert.assertEquals(3, testList.size());
    }
}