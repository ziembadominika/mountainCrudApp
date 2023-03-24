package com.crudApp.mountain.mapper;

import com.crudApp.mountain.domain.MountainRange;
import com.crudApp.mountain.domain.MountainRangeDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(uses = {MountainMapper.class},componentModel = "spring")
public interface MountainRangeMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "rangeName", target = "rangeName")
    @Mapping(source = "mountains", target = "mountains")
    MountainRange mapToMountainRange(MountainRangeDto mountainRangeDto);

    @InheritInverseConfiguration(name = "mapToMountainRange")
    MountainRangeDto mapToMountainRangeDto(MountainRange mountainRange);

    @IterableMapping(elementTargetType = MountainRangeDto.class)
    List<MountainRangeDto> mapToMountainRangeDtoList(final List<MountainRange> mountainRangesList);
}
