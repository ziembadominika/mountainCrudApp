package com.crudApp.mountain.controller;

import com.crudApp.mountain.domain.ContinentDto;
import com.crudApp.mountain.service.ContinentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mountainApp")
public class ContinentController {

    @Autowired
    ContinentService continentService;

    @GetMapping(value = "/getAllContinents")
    public List<ContinentDto> getAllContinents() {
        return continentService.getAllContinents();
    }

    @GetMapping(value = "/getContinent")
    public ContinentDto getContinent(@RequestParam Long id) {
        return continentService.getContinent(id);
    }

    @PostMapping("/createContinent")
    public void createContinent(@RequestBody ContinentDto continentDto) {
        continentService.saveContinent(continentDto);
    }

    @PutMapping("/updateContinent")
    public ContinentDto updateContinent(@RequestBody ContinentDto continentDto) {
        return continentService.updateContinent(continentDto);
    }

    @DeleteMapping("/deleteContinent")
    public void deleteContinent(@RequestParam Long id) {
        continentService.deleteContinent(id);
    }

}
