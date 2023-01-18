package com.crudApp.mountain.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class CountryTest {

    @Test
    public void shouldGetMountainsFromCountry() {

        //Given
        List<Mountain> tatry = new ArrayList<>();
        Mountain rysy = new Mountain(1L, "Rysy", 2499, "Poland");
        Mountain łomnica = new Mountain(2L, "Łomnica", 2634, "Slovakia");
        tatry.add(rysy);
        tatry.add(łomnica);

        List<Mountain> sudety = new ArrayList<>();
        Mountain śnieżnik = new Mountain(3L, "Śnieżnik", 1423, "Poland");
        Mountain śnieżka = new Mountain(4L, "Śnieżka", 1603, "Poland");
        sudety.add(śnieżnik);
        sudety.add(śnieżka);

        MountainRange tatraMountains = new MountainRange(1L, "Tatra Mountains", tatry);
        MountainRange sudetyMountains = new MountainRange(2L, "Sudety", sudety);


        List<MountainRange> polishMountains = new ArrayList<>();
        polishMountains.add(tatraMountains);
        polishMountains.add(sudetyMountains);

        Country poland = new Country(1L, "Poland", polishMountains);

        //When
        List<Mountain> testList = poland.getMountainsFromCountry("Poland");

        //Then
        Assert.assertEquals(3, testList.size());
    }

}