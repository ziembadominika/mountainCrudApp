package com.crudApp.mountain.controller;

import com.crudApp.mountain.domain.Mountain;
import com.crudApp.mountain.domain.MountainDto;
import com.crudApp.mountain.mapper.MountainMapper;
import com.crudApp.mountain.service.MountainService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/mountainApp")
public class MountainController {

    @Autowired
    private MountainService mountainService;


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
        mountainService.saveMountain(mountainDto);
    }
}
