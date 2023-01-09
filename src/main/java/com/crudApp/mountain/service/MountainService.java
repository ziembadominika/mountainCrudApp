package com.crudApp.mountain.service;

import com.crudApp.mountain.domain.Mountain;
import com.crudApp.mountain.domain.MountainDto;
import com.crudApp.mountain.mapper.MountainMapper;
import com.crudApp.mountain.repository.MountainRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Data
public class MountainService {

    private MountainRepository mountainRepository;

    private MountainMapper mountainMapper;

    @Autowired
    public MountainService(MountainRepository mountainRepository, MountainMapper mountainMapper) {
        this.mountainRepository = mountainRepository;
        this.mountainMapper = mountainMapper;
    }

    public List<MountainDto> getAllMountains() {
        return mountainMapper.mapToMountainDtoList(mountainRepository.findAll());
    }

    public MountainDto getMountain(final Long id){
        return mountainMapper.mapToMountainDto(mountainRepository.getReferenceById(id));
    }

    public List<MountainDto> findAllMountainByNameContaining(String name){
        return mountainMapper.mapToMountainDtoList(mountainRepository.findByNameContaining(name));
    }

    public void saveMountain(final MountainDto mountainDto) {
        Mountain mountain = mountainMapper.mapToMountain(mountainDto);
        mountainRepository.save(mountain);
    }

    public MountainDto updateMountain(final MountainDto mountainDto){
        Mountain mountain = mountainMapper.mapToMountain(mountainDto);
        mountainRepository.save(mountain);
        return mountainMapper.mapToMountainDto(mountain);
    }

    public void deleteMountain(Long id){
        mountainRepository.deleteById(id);
    }

}