package com.crudApp.mountain.controller;

import com.crudApp.mountain.domain.MountainDto;
import com.crudApp.mountain.exception.MountainNotFoundException;
import com.crudApp.mountain.service.MountainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mountainApp")
public class MountainController {

    private final MountainService mountainService;

    @GetMapping(value = "/getAllMountains")
    public List<MountainDto> getMountains() {
        return mountainService.getAllMountains();
    }

    @GetMapping(value = "/getMountain")
    public MountainDto getMountain(@RequestParam Long id) {
        return mountainService.getMountain(id);
    }

    @DeleteMapping(value = "/deleteMountain")
    public void deleteMountain(@RequestParam Long id) {
        mountainService.deleteMountain(id);
    }

    @PutMapping(value = "/updateMountain")
    public MountainDto updateMountain(@RequestBody MountainDto mountainDto) {
        return mountainService.updateMountain(mountainDto);
    }

    @PostMapping(value = "/createMountain")
    public void createMountain(@RequestBody MountainDto mountainDto) {
        mountainService.createMountain(mountainDto);
    }

    @GetMapping(value = "/getMountainByName")
    public Optional<List<MountainDto>> getMountainByName(@RequestParam String name) {
        return mountainService.findMountainByNameLike(name);
    }

    @GetMapping(value = "/getMountainsByHeightAbove")
    public List<MountainDto> getMountainsByHeightAbove(@RequestParam int height) {
        return mountainService.getMountainByHeight(height);
    }

    @GetMapping(value = "getRatingForMountain")
    public double getUserRatingForMountain(@RequestParam Long mountainId) throws MountainNotFoundException {
        return mountainService.getUserRatingForMountain(mountainId);
    }

    @GetMapping(value = "/getMountainsByCountry")
    public List<MountainDto> getMountainsByCountry(@RequestParam String countryName) {
        return mountainService.getMountainsByCountry(countryName);
    }

    @GetMapping(value = "/getMountainsByContinent")
    public List<MountainDto> getMountainsByContinent(@RequestParam String continentName) {
        return mountainService.getMountainsByContinent(continentName);
    }
}
