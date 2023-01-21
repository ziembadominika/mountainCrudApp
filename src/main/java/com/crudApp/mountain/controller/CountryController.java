package com.crudApp.mountain.controller;

import com.crudApp.mountain.domain.CountryDto;
import com.crudApp.mountain.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mountainApp")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping(value = "/getAllCountries")
    public List<CountryDto> getAllCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping(value = "/getCountry")
    public CountryDto getCountry(@RequestParam Long id) {
        return countryService.getCountry(id);
    }

    @PostMapping(value = "/createCountry")
    public void createCountry(@RequestBody CountryDto countryDto) {
        countryService.saveCountry(countryDto);
    }

    @PutMapping(value = "/updateCountry")
    public CountryDto updateCountry(@RequestBody CountryDto countryDto) {
        return countryService.updateCountry(countryDto);
    }

    @DeleteMapping(value = "/deleteCountry")
    public void deleteCountry(@RequestParam Long id) {
        countryService.deleteCountry(id);
    }
}
