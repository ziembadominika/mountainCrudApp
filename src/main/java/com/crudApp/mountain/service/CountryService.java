package com.crudApp.mountain.service;

import com.crudApp.mountain.domain.Country;
import com.crudApp.mountain.domain.CountryDto;
import com.crudApp.mountain.domain.Mountain;
import com.crudApp.mountain.domain.MountainDto;
import com.crudApp.mountain.mapper.CountryMapper;
import com.crudApp.mountain.repository.CountryRepository;
import com.crudApp.mountain.repository.MountainRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class CountryService {

    private CountryMapper countryMapper;

    private CountryRepository countryRepository;

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
}
