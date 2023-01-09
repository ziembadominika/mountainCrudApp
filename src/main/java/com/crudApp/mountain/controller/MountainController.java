package com.crudApp.mountain.controller;

import com.crudApp.mountain.domain.MountainDto;
import com.crudApp.mountain.service.MountainService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@AllArgsConstructor
@RestController
@RequestMapping("/mountainApp")
public class MountainController {

    @Autowired
    private MountainService mountainService;


    @RequestMapping(value = "/getAllMountains", method = GET)
    @ResponseBody
    public List<MountainDto> getMountains() {
        return mountainService.getAllMountains();
    }

    @RequestMapping(value = "/getMountain", method = GET)
    @ResponseBody
    public MountainDto getMountain(Long id) {
        return mountainService.getMountain(id);
    }

    @RequestMapping(value = "/deleteMountain", method = DELETE)
    @ResponseBody
    public void deleteMountain(Long mountainId) {
    }
//
//    @RequestMapping(value = "/putMountain", method = PUT)
//    @ResponseBody
//    public MountainDto updateMountain(MountainDto mountainDto) {
//        return new MountainDto(mountainService.);
//    }

//    @RequestMapping(value = "/postMountain", method = POST, consumes = APPLICATION_JSON_VALUE)
//    public void createMountain(@RequestBody MountainDto mountainDto) {
//        mountainService.saveMountain(mountainMapper.mapToMountain(mountainDto));
//    }
}
