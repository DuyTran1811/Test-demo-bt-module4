package com.test.controller;

import com.test.model.City;
import com.test.model.Country;
import com.test.service.city.ICityService;
import com.test.service.country.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/city")
public class CityController {
    private final ICityService cityService;
    private final ICountryService countryService;

    @Autowired
    public CityController(ICityService cityService, ICountryService countryService) {
        this.cityService = cityService;
        this.countryService = countryService;
    }

    @ModelAttribute("country")
    public Iterable<Country> showListCountry() {
        return countryService.findAll();
    }

    @GetMapping("list")
    public ModelAndView showList() {
        Iterable<City> cityList = cityService.findAll();
        return new ModelAndView("list-city", "city", cityList);
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<City>> getAllCity(@RequestParam(required = false) String keyword) {
        Iterable<City> cities;
        if (keyword != null) {
            cities = cityService.searchByName(keyword);
        } else {
            cities = cityService.findAll();
        }
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<City> addCity(@RequestBody City city) {
        City newCity = cityService.save(city);
        return new ResponseEntity<>(newCity, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCityId(@PathVariable("id") Integer id) {
        City finCity = cityService.findById(id);
        return new ResponseEntity<>(finCity, HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editCity(@RequestBody City city) {
        City editCity = cityService.save(city);
        return new ResponseEntity<>(editCity, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable("id") Integer id) {
        cityService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
