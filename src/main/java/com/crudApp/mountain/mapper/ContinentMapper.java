package com.crudApp.mountain.mapper;

import com.crudApp.mountain.domain.Continent;
import com.crudApp.mountain.domain.ContinentDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContinentMapper {
    public Continent mapToContinent(ContinentDto continentDto) {
        return new Continent(
                continentDto.getId(),
                continentDto.getContinentName());
    }

    public ContinentDto mapToContinentDto(Continent continent) {
        return new ContinentDto(
                continent.getId(),
                continent.getContinentName());
    }

    public List<ContinentDto> mapToContinentDtoList(List<Continent> continentsList) {
        return continentsList.stream()
                .map(c-> new ContinentDto(c.getId(), c.getContinentName()))
                .collect(Collectors.toList());
    }
}
