package com.crudApp.mountain.controller;

import com.crudApp.mountain.domain.MountainDto;
import com.crudApp.mountain.domain.MountainRangeDto;
import com.crudApp.mountain.service.MountainRangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/mountainApp")
public class MountainRangeController {

    private MountainRangeService mountainRangeService;

    @GetMapping(value = "/getAllMountainRanges")
    private List<MountainRangeDto> getAllMountainRanges() {
        return mountainRangeService.getAllMountainRanges();
    }

    @GetMapping(value = "/getMountainRange")
    private MountainRangeDto getMountainRange(@RequestParam Long id) {
        return mountainRangeService.getMountainRange(id);
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
