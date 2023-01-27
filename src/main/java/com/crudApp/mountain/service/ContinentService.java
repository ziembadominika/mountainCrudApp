package com.crudApp.mountain.service;

import com.crudApp.mountain.domain.Continent;
import com.crudApp.mountain.domain.ContinentDto;
import com.crudApp.mountain.domain.Mountain;
import com.crudApp.mountain.domain.MountainDto;
import com.crudApp.mountain.mapper.ContinentMapper;
import com.crudApp.mountain.mapper.MountainMapper;
import com.crudApp.mountain.repository.ContinentRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ContinentService {
    private ContinentMapper continentMapper;

    private ContinentRepository continentRepository;

    private Continent continent;

    private MountainMapper mountainMapper;

    @Autowired
    public ContinentService(ContinentMapper continentMapper, ContinentRepository continentRepository) {
        this.continentMapper = continentMapper;
        this.continentRepository = continentRepository;
    }

    public List<ContinentDto> getAllContinents(){
        return continentMapper.mapToContinentDtoList(continentRepository.findAll());
    }

    public ContinentDto getContinent (Long id){
        return continentMapper.mapToContinentDto(continentRepository.getReferenceById(id));
    }

    public void saveContinent (ContinentDto continentDto){
        Continent continent = continentMapper.mapToContinent(continentDto);
        continentRepository.save(continent);
    }

    public ContinentDto updateContinent (ContinentDto continentDto){
        Continent continent = continentMapper.mapToContinent(continentDto);
        continentRepository.save(continent);
        return continentMapper.mapToContinentDto(continent);
    }

    public void deleteContinent(Long id){
        continentRepository.deleteById(id);
    }

    public List<MountainDto>getMountainsFromContinent(String continentName){
        return mountainMapper.mapToMountainDtoList(continent.getMountainFromContinent(continentName));
    }
}
