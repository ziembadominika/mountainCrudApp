package com.crudApp.mountain.mapper;

import com.crudApp.mountain.domain.MountainRange;
import com.crudApp.mountain.domain.MountainRangeDto;
import org.mapstruct.*;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface MountainRangeMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "rangeName", target = "rangeName")
    @Mapping(ignore = true, target = "mountains")
    MountainRange mapToMountainRange(MountainRangeDto mountainRangeDto);

    @InheritInverseConfiguration
    @Mapping(ignore = true, target = "mountains")
    MountainRangeDto mapToMountainRangeDto(MountainRange mountainRange);

    @IterableMapping(elementTargetType = MountainRangeDto.class)
    List<MountainRangeDto> mapToMountainRangeDtoList(Optional<List<MountainRange>> mountainRangesList);
}
