package com.crudApp.mountain.service;

import com.crudApp.mountain.domain.MountainDto;
import com.crudApp.mountain.domain.MountainRange;
import com.crudApp.mountain.domain.MountainRangeDto;
import com.crudApp.mountain.exception.MountainNotFoundException;
import com.crudApp.mountain.exception.MountainRangeNotFoundException;
import com.crudApp.mountain.mapper.MountainMapper;
import com.crudApp.mountain.mapper.MountainRangeMapper;
import com.crudApp.mountain.repository.MountainRangeRepository;
import com.crudApp.mountain.repository.MountainRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class MountainRangeService {

    private MountainMapper mountainMapper;
    private MountainRangeRepository mountainRangeRepository;
    private MountainRangeMapper mountainRangeMapper;
    @Autowired
    public MountainRangeService(MountainRangeRepository mountainRangeRepository, MountainRangeMapper mountainRangeMapper, MountainMapper mountainMapper) {
        this.mountainRangeRepository = mountainRangeRepository;
        this.mountainRangeMapper = mountainRangeMapper;
        this.mountainMapper = mountainMapper;
    }

    public List<MountainRangeDto> getAllMountainRanges() {
        return mountainRangeMapper.mapToMountainRangeDtoList(mountainRangeRepository.findAll());
    }

    public MountainRangeDto getMountainRange(Long id){
        return mountainRangeMapper.mapToMountainRangeDto(mountainRangeRepository.getReferenceById(id));
    }

    public List<MountainRangeDto> findMountainRangeByNameLike(String name){
        List<MountainRange> mountainRanges = mountainRangeRepository.findByRangeNameLike(name + "%");
        return mountainRangeMapper.mapToMountainRangeDtoList(mountainRanges);
    }

    public void createMountainRange(MountainRangeDto mountainRangeDto) {
        MountainRange mountainRange = mountainRangeMapper.mapToMountainRange(mountainRangeDto);
        mountainRangeRepository.save(mountainRange);
    }

    public MountainRangeDto updateMountainRange(MountainRangeDto mountainRangeDto){
        MountainRange mountainRange = mountainRangeMapper.mapToMountainRange(mountainRangeDto);
        mountainRangeRepository.save(mountainRange);
        return mountainRangeMapper.mapToMountainRangeDto(mountainRange);
    }

    public void deleteMountainRange(Long id){
        mountainRangeRepository.deleteById(id);
    }

    public List<MountainDto>getMountainsFromRange(Long id) {
     MountainRange mountainRange = mountainRangeRepository.findById(id).orElseThrow(MountainRangeNotFoundException::new);
     return mountainMapper.mapToMountainDtoList(mountainRange.getMountains());
    }
}
