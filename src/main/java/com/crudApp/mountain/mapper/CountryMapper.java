package com.crudApp.mountain.mapper;

import com.crudApp.mountain.domain.Country;
import com.crudApp.mountain.domain.CountryDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CountryMapper {
    public Country mapToCountry(CountryDto countryDto) {
        return new Country(
                countryDto.getId(),
                countryDto.getCountryName());
    }

    public CountryDto mapToCountryDto(final Country country) {
        return new CountryDto(
                country.getId(),
                country.getCountryName());
    }

    public List<CountryDto> mapToCountryDtoList(final List<Country> countriesList) {
        return countriesList.stream()
                .map(c-> new CountryDto(c.getId(), c.getCountryName()))
                .collect(Collectors.toList());
    }
}
