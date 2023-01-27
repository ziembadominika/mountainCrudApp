package com.crudApp.mountain.mapper;

import com.crudApp.mountain.domain.Mountain;
import com.crudApp.mountain.domain.MountainDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class MountainMapper {

    public Mountain mapToMountain(MountainDto mountainDto) {
        return new Mountain(
                mountainDto.getId(),
                mountainDto.getName(),
                mountainDto.getHeight(),
                mountainDto.getCountry(),
                mountainDto.getMountainRange(),
                mountainDto.getUserRatings(),
                mountainDto.getUsers());
    }

    public MountainDto mapToMountainDto(final Mountain mountain) {
        return new MountainDto(
                mountain.getId(),
                mountain.getName(),
                mountain.getHeight(),
                mountain.getCountry(),
                mountain.getMountainRange(),
                mountain.getUserRatings(),
                mountain.getUsers());
    }

    public List<MountainDto> mapToMountainDtoList(final List<Mountain> mountainList) {
        return mountainList.stream()
                .map(m -> new MountainDto(m.getId(), m.getName(), m.getHeight(), m.getCountry(), m.getMountainRange(), m.getUserRatings(), m.getUsers()))
                .collect(Collectors.toList());
    }
}
