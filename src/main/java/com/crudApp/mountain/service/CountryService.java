package com.crudApp.mountain.service;

import com.crudApp.mountain.domain.*;
import com.crudApp.mountain.mapper.CountryMapper;
import com.crudApp.mountain.mapper.MountainMapper;
import com.crudApp.mountain.repository.CountryRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class CountryService {

    private CountryMapper countryMapper;

    private CountryRepository countryRepository;

    private MountainMapper mountainMapper;

    private Country country;

    @Autowired
    public CountryService(CountryMapper countryMapper, CountryRepository countryRepository) {
        this.countryMapper = countryMapper;
        this.countryRepository = countryRepository;
    }

    public List<CountryDto> getAllCountries(){
        return countryMapper.mapToCountryDtoList(countryRepository.findAll());
    }

    public CountryDto getCountry (Long id){
        return countryMapper.mapToCountryDto(countryRepository.getReferenceById(id));
    }

    public void saveCountry (CountryDto countryDto){
        Country country = countryMapper.mapToCountry(countryDto);
        countryRepository.save(country);
    }

    public CountryDto updateCountry (CountryDto countryDto){
        Country country = countryMapper.mapToCountry(countryDto);
        countryRepository.save(country);
        return countryMapper.mapToCountryDto(country);
    }

    public void deleteCountry(Long id){
        countryRepository.deleteById(id);
    }

    public List<MountainDto>getMountainsFromCountry(String countryName){
        return mountainMapper.mapToMountainDtoList(country.getMountainsFromCountry(countryName));
    }

    public List<CountryDto>findByCountryNameLike(String name){
        return countryMapper.mapToCountryDtoList(countryRepository.findByCountryNameLike(name));
    }
}
