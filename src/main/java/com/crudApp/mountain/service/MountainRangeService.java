package com.crudApp.mountain.service;

import com.crudApp.mountain.domain.MountainDto;
import com.crudApp.mountain.domain.MountainRange;
import com.crudApp.mountain.domain.MountainRangeDto;
import com.crudApp.mountain.exception.MountainRangeNotFoundException;
import com.crudApp.mountain.mapper.MountainMapper;
import com.crudApp.mountain.mapper.MountainRangeMapper;
import com.crudApp.mountain.repository.MountainRangeRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class MountainRangeService {

    private final MountainMapper mountainMapper;
    private final MountainRangeRepository mountainRangeRepository;
    private final MountainRangeMapper mountainRangeMapper;

    public static Pageable firstPage = PageRequest.of(1, 5, Sort.by("name"));

    public List<MountainRangeDto> getAllMountainRanges() {
        return mountainRangeMapper.mapToMountainRangeDtoList(mountainRangeRepository.findAll());
    }

    public MountainRangeDto getMountainRange(Long id) {
        return mountainRangeMapper.mapToMountainRangeDto(mountainRangeRepository.getReferenceById(id));
    }

    public Optional<List<MountainRangeDto>> findMountainRangeByNameLike(String name) {
        Optional<List<MountainRange>> mountainRanges = mountainRangeRepository.findByRangeNameContainingIgnoreCase(name, firstPage);
        return mountainRangeMapper.mapToMountainRangeDtoList(mountainRanges);
    }

    public void createMountainRange(MountainRangeDto mountainRangeDto) {
        MountainRange mountainRange = mountainRangeMapper.mapToMountainRange(mountainRangeDto);
        mountainRangeRepository.save(mountainRange);
    }

    public MountainRangeDto updateMountainRange(MountainRangeDto mountainRangeDto) {
        MountainRange mountainRange = mountainRangeMapper.mapToMountainRange(mountainRangeDto);
        mountainRangeRepository.save(mountainRange);
        return mountainRangeMapper.mapToMountainRangeDto(mountainRange);
    }

    public void deleteMountainRange(Long id) {
        mountainRangeRepository.deleteById(id);
    }

    public List<MountainDto> getMountainsFromRange(Long id) {
        MountainRange mountainRange = mountainRangeRepository.findById(id).orElseThrow(MountainRangeNotFoundException::new);
        return mountainMapper.mapToMountainDtoList(mountainRange.getMountains());
    }
}
