package com.crudApp.mountain.mapper;

import com.crudApp.mountain.domain.Mountain;
import com.crudApp.mountain.domain.MountainDto;
import com.crudApp.mountain.domain.MountainRange;
import com.crudApp.mountain.domain.MountainRangeDto;
import org.mapstruct.*;

import java.util.List;
import java.util.Optional;

@Mapper(uses = {MountainRangeMapper.class}, componentModel = "spring")
public interface MountainMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "mountainName", target = "name")
    @Mapping(source = "height", target = "height")
    @Mapping(source = "mountainRange", target = "mountainRange")
    @Mapping(source = "country", target = "country")
    @Mapping(source = "continent", target = "continent")
    @Mapping(ignore = true, target = "usersRatings")
    Mountain mapToMountain(MountainDto mountainDto);

    @InheritInverseConfiguration
    @Mapping(ignore = true, target = "usersRatings")
    MountainDto mapToMountainDto(final Mountain mountain);
    @IterableMapping(elementTargetType = MountainDto.class)
    List<MountainDto> mapToMountainDtoList(List<Mountain> mountainList);

    default Optional<List<MountainDto>> mapToMountainDtoList(Optional<List<Mountain>> mountainList) {
        return mountainList.map(this::mapToMountainDtoList);
    }
}
