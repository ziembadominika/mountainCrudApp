package com.crudApp.mountain.service;

import com.crudApp.mountain.domain.Country;
import com.crudApp.mountain.domain.CountryDto;
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

    public void setUp() {
        countryService = new CountryService(countryMapper, countryRepository);
        poland = new Country(1L, "Poland");
        Country slovakia = new Country(2L, "Slovakia");
        countries.add(poland);
        countries.add(slovakia);
    }

    @Test
    public void getAllCountries() {
        //Given
        countryService = new CountryService(countryMapper, countryRepository);
        poland = new Country(1L, "Poland");
        Country slovakia = new Country(2L, "Slovakia");
        countries.add(poland);
        countries.add(slovakia);
        when(countryRepository.findAll()).thenReturn(countries);
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
        poland = new Country(1L, "Poland");
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
        poland = new Country(1L, "Poland");
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
        CountryDto poland = new CountryDto(3L, "Poland");
        //When
        CountryDto updatedCountry = countryService.updateCountry(poland);
        //Then
        Assert.assertEquals(3L, updatedCountry.getId());
    }

    @Test
    public void deleteCountry() {
        //Given
        countryService = new CountryService(countryMapper, countryRepository);
        poland = new Country(3L, "Poland");
        Long countryId = poland.getId();
        //When
        countryService.deleteCountry(countryId);
        //Then
        verify(countryRepository, times(1)).deleteById(countryId);
    }
}