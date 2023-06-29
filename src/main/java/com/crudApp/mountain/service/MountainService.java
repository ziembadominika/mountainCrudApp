package com.crudApp.mountain.service;

import com.crudApp.mountain.domain.Mountain;
import com.crudApp.mountain.domain.MountainDto;
import com.crudApp.mountain.exception.MountainNotFoundException;
import com.crudApp.mountain.mapper.MountainMapper;
import com.crudApp.mountain.repository.MountainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MountainService {

    private final MountainRepository mountainRepository;
    private final MountainMapper mountainMapper;
    public static Pageable firstPage = PageRequest.of(1, 5, Sort.by("name"));

    public List<MountainDto> getAllMountains() {
        return mountainMapper.mapToMountainDtoList(mountainRepository.findAll());
    }

    public MountainDto getMountain(Long id) {
        return mountainMapper.mapToMountainDto(mountainRepository.getReferenceById(id));
    }

    public Optional<List<MountainDto>> findMountainByNameLike(String name) {
        Optional<List<Mountain>> mountains = mountainRepository.searchByName(name, firstPage);
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
        List<MountainDto> mountains = mountainMapper.mapToMountainDtoList(mountainRepository.findAll());
        return mountains.stream()
                .filter(m -> m.getCountry().equals(countryName))
                .collect(Collectors.toList());
    }

    public List<MountainDto> getMountainsByContinent(String continentName) {
        List<MountainDto> mountainDtoList = mountainMapper.mapToMountainDtoList(mountainRepository.findAll());
        return mountainDtoList.stream()
                .filter(m -> m.getContinent().equals(continentName))
                .collect(Collectors.toList());

    }

}