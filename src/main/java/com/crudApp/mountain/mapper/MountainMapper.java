package com.crudApp.mountain.mapper;

import com.crudApp.mountain.domain.Mountain;
import com.crudApp.mountain.domain.MountainDto;
import javafx.scene.effect.SepiaTone;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class MountainMapper {

    public Mountain mapToMountain(MountainDto mountainDto) {
        return new Mountain(
                mountainDto.getId(),
                mountainDto.getName(),
                mountainDto.getHeight(),
                mountainDto.getMountainRange(),
                mountainDto.getCountry(),
                mountainDto.getContinent(),
                mountainDto.getUserRatings(),
                mountainDto.getUsers());
    }

    public MountainDto mapToMountainDto(final Mountain mountain) {
        return new MountainDto(
                mountain.getId(),
                mountain.getName(),
                mountain.getHeight(),
                mountain.getMountainRange(),
                mountain.getCountry(),
                mountain.getContinent(),
                mountain.getUserRatings(),
                mountain.getUsers());
    }

    public List<MountainDto> mapToMountainDtoList(final List<Mountain> mountainList) {
        return mountainList.stream()
                .map(m -> new MountainDto(m.getId(), m.getName(), m.getHeight(), m.getMountainRange(), m.getCountry(), m.getContinent(),
                        m.getUserRatings(), m.getUsers()))
                .collect(Collectors.toList());
    }

    public Set<MountainDto>mapToMountainDtoSet(final Set<Mountain>mountains){
        return mountains.stream().map(m->mapToMountainDto(m))
                .collect(Collectors.toSet());
    }
}
