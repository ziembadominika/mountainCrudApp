package com.crudApp.mountain.controller;

import com.crudApp.mountain.domain.MountainDto;
import com.crudApp.mountain.domain.MountainRangeDto;
import com.crudApp.mountain.service.MountainRangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/mountainApp")
public class MountainRangeController {

    private final MountainRangeService mountainRangeService;

    @GetMapping(value = "/getAllMountainRanges")
    private Optional<List<MountainRangeDto>> getAllMountainRanges() {
        return mountainRangeService.getAllMountainRanges();
    }

    @GetMapping(value = "/getMountainRange")
    private MountainRangeDto getMountainRange(@RequestParam Long id) {
        return mountainRangeService.getMountainRange(id);
    }

    @GetMapping(value = "/getRangeByName")
    private Optional<List<MountainRangeDto>> findRangesByName(@RequestParam String name) {
        return mountainRangeService.findMountainRangeByNameLike(name);
    }

    @PostMapping(value = "/createMountainRange")
    private void createMountainRange(@RequestBody MountainRangeDto mountainRangeDto) {
        mountainRangeService.createMountainRange(mountainRangeDto);
    }

    @PutMapping(value = "/updateMountainRange")
    private MountainRangeDto updateMountainRange(@RequestBody MountainRangeDto mountainRangeDto) {
        return mountainRangeService.updateMountainRange(mountainRangeDto);
    }

    @DeleteMapping(value = "/deleteMountainRange")
    private void deleteMountainRange(@RequestParam Long id) {
        mountainRangeService.deleteMountainRange(id);
    }

    @GetMapping(value = "/getMountainsFromRange")
    private List<MountainDto> getMountainsFromRange(@RequestParam Long id) {
        return mountainRangeService.getMountainsFromRange(id);
    }
}
