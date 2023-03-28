package com.crudApp.mountain.mapper;

import com.crudApp.mountain.domain.Mountain;
import com.crudApp.mountain.domain.MountainDto;
import org.mapstruct.*;

import java.util.List;

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
}
