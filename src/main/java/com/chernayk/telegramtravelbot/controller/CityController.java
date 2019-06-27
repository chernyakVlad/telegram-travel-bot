package com.chernayk.telegramtravelbot.controller;

import com.chernayk.telegramtravelbot.model.City;
import com.chernayk.telegramtravelbot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cities")
public class CityController {

    private CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Long id) {

        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<City> result = cityService.getCityById(id);

        if(result.isPresent()){
            return new ResponseEntity<City>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "")
    public ResponseEntity<List<City>> getCityById() {

        List<City> result = cityService.getAllCities();

        if(result != null){
            return new ResponseEntity<List<City>>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<City> saveCity(@RequestBody City city) {

        if(city == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(cityService.getCityByName(city.getName()).isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<City>(cityService.saveCity(city), HttpStatus.CREATED);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<City> deleteCity(@PathVariable Long id) {

        if(id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        cityService.deleteCity(id);

        return new ResponseEntity<City>(HttpStatus.OK);
    }
}
