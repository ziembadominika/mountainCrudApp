package com.crudApp.mountain.mapper;

import com.crudApp.mountain.domain.Country;
import com.crudApp.mountain.domain.CountryDto;
import com.crudApp.mountain.repository.CountryRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CountryMapper {
    private final CountryRepository countryRepository;

    public CountryMapper(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Country mapToCountry(CountryDto countryDto) {
        return new Country(
                countryDto.getId(),
                countryDto.getCountryName(),
                countryDto.getMountainRanges(),
                countryDto.getContinent());
    }

    public CountryDto mapToCountryDto(final Country country) {
        return new CountryDto(
                country.getId(),
                country.getCountryName(),
                country.getMountainRanges(),
                country.getContinent());
    }

    public List<CountryDto> mapToCountryDtoList(final List<Country> countriesList) {
        return countriesList.stream()
                .map(c-> new CountryDto(c.getId(), c.getCountryName(), c.getMountainRanges(), c.getContinent()))
                .collect(Collectors.toList());
    }
}
