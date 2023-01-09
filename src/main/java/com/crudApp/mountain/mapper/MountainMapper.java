package com.crudApp.mountain.mapper;

import com.crudApp.mountain.domain.Mountain;
import com.crudApp.mountain.domain.MountainDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Component
public class MountainMapper {


    public Mountain mapToMountain(final MountainDto mountainDto){
        return new Mountain(
                mountainDto.getId(),
                mountainDto.getName(),
                mountainDto.getHeight(),
                mountainDto.getCountry(),
                mountainDto.getMountainRange());
    }

    public MountainDto mapToMountainDto(final Mountain mountain){
        return new MountainDto(
                mountain.getId(),
                mountain.getName(),
                mountain.getHeight(),
                mountain.getCountry(),
                mountain.getMountainRange());
    }
    public List<MountainDto> mapToMountainDtoList(final List<Mountain> mountainList){
        return mountainList.stream()
                .map(m-> new MountainDto(m.getId(), m.getName(), m.getHeight(), m.getCountry(), m.getMountainRange()))
                .collect(Collectors.toList());
    }
}
