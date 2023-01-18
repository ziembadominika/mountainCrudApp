package com.crudApp.mountain.service;

import com.crudApp.mountain.domain.MountainDto;
import com.crudApp.mountain.domain.MountainRange;
import com.crudApp.mountain.domain.MountainRangeDto;
import com.crudApp.mountain.mapper.MountainMapper;
import com.crudApp.mountain.mapper.MountainRangeMapper;
import com.crudApp.mountain.repository.MountainRangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MountainRangeService {

    private MountainRangeMapper mountainRangeMapper;
    private MountainRangeRepository mountainRangeRepository;

    private MountainRange mountainRange;

    private MountainMapper mountainMapper;

    @Autowired
    public MountainRangeService(MountainRangeMapper mountainRangeMapper, MountainRangeRepository mountainRangeRepository) {
        this.mountainRangeMapper = mountainRangeMapper;
        this.mountainRangeRepository = mountainRangeRepository;
    }

    public List<MountainRangeDto> getAllMountainRanges() {
        return mountainRangeMapper.mapToMountainRangeDtoList(mountainRangeRepository.findAll());
    }

    public MountainRangeDto getMountainRange(Long id){
        return mountainRangeMapper.mapToMountainRangeDto(mountainRangeRepository.getReferenceById(id));
    }

    public List<MountainRangeDto> findMountainRangeByNameLike(String name){
        List<MountainRange> mountainRanges = mountainRangeRepository.findByNameLike(name + "%");
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

    public List<MountainDto> getMountainsFromRange(String rangeName) {
        return mountainMapper.mapToMountainDtoList(mountainRange.getMountainsFromRange(rangeName));
    }
}
