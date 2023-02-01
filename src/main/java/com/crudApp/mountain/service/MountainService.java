package com.crudApp.mountain.service;

import com.crudApp.mountain.domain.Mountain;
import com.crudApp.mountain.domain.MountainDto;
import com.crudApp.mountain.exception.MountainNotFoundException;
import com.crudApp.mountain.mapper.MountainMapper;
import com.crudApp.mountain.repository.MountainRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@Data
public class MountainService {

    private MountainRepository mountainRepository;

    private MountainMapper mountainMapper;

    private Mountain mountain;

    @Autowired
    public MountainService(MountainRepository mountainRepository, MountainMapper mountainMapper) {
        this.mountainRepository = mountainRepository;
        this.mountainMapper = mountainMapper;
    }

    public List<MountainDto> getAllMountains() {
        return mountainMapper.mapToMountainDtoList(mountainRepository.findAll());
    }

    public MountainDto getMountain(Long id) {
//        try {
//            Mountain mountain = mountainRepository.getReferenceById(id);
//        } catch (NumberFormatException e) {
//            System.out.println("This is not a number");
//        }
//        return mountainMapper.mapToMountainDto(mountain);

        return mountainMapper.mapToMountainDto(mountainRepository.getReferenceById(id));
    }

    public List<MountainDto> findMountainByNameLike(String name) {
        List<Mountain> mountains = mountainRepository.findByNameLike(name + "%");
        return mountainMapper.mapToMountainDtoList(mountains);
    }

    public void createMountain(MountainDto mountainDto) {
        Mountain mountain = mountainMapper.mapToMountain(mountainDto);
        mountainRepository.save(mountain);
    }

    public MountainDto updateMountain(MountainDto mountainDto) {
        Mountain mountain = mountainMapper.mapToMountain(mountainDto);
        mountainRepository.save(mountain);
        return mountainMapper.mapToMountainDto(mountain);
    }

    public void deleteMountain(Long id) {
        mountainRepository.deleteById(id);
    }

    public List<MountainDto> getMountainByHeight(int height) {
        List<Mountain> mountainList = mountainRepository.findAll();
        List<MountainDto> mountainDtoList = mountainMapper.mapToMountainDtoList(mountainList);
        return mountainDtoList.stream().filter(m -> m.getHeight() >= height).collect(Collectors.toList());
    }

    public double getUserRatingForMountain(Long mountainId) {
        Mountain mountain = mountainRepository.findById(mountainId).orElseThrow(MountainNotFoundException::new);
        return mountain.userRatingAverage();
    }

    public List<MountainDto> getMountainsByCountry(String countryName) {
        List<Mountain> mountains = mountainRepository.findAll();
        mountains.stream()
                .filter(m -> m.getCountry().equals(countryName))
                .collect(Collectors.toList());
        return mountainMapper.mapToMountainDtoList(mountains);
    }

    public List<MountainDto> getMountainsByContinent(String continentName) {
        List<MountainDto>mountainDtoList = mountainMapper.mapToMountainDtoList(mountainRepository.findAll());
        return  mountainDtoList.stream()
                .filter(m -> m.getContinent().equals(continentName))
                .collect(Collectors.toList());

    }

}