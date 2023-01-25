package com.crudApp.mountain.mapper;

import com.crudApp.mountain.domain.MountainRange;
import com.crudApp.mountain.domain.MountainRangeDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MountainRangeMapper {
    public MountainRange mapToMountainRange(MountainRangeDto mountainRangeDto) {
        return new MountainRange(
                mountainRangeDto.getId(),
                mountainRangeDto.getRangeName(),
                mountainRangeDto.getMountains());
    }

    public MountainRangeDto mapToMountainRangeDto(MountainRange mountainRange) {
        return new MountainRangeDto(
                mountainRange.getId(),
                mountainRange.getRangeName(),
                mountainRange.getMountains());
    }

    public List<MountainRangeDto> mapToMountainRangeDtoList(final List<MountainRange> mountainRangesList) {
        return mountainRangesList.stream()
                .map(r -> new MountainRangeDto(r.getId(), r.getRangeName(), r.getMountains()))
                .collect(Collectors.toList());
    }
}
